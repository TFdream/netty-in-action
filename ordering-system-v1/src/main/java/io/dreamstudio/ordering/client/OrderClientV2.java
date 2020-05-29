package io.dreamstudio.ordering.client;

import io.dreamstudio.ordering.client.codec.OrderFrameDecoder;
import io.dreamstudio.ordering.client.codec.OrderFrameEncoder;
import io.dreamstudio.ordering.client.codec.OrderProtocolDecoder;
import io.dreamstudio.ordering.client.codec.OrderProtocolEncoder;
import io.dreamstudio.ordering.client.handler.OrderClientHandler;
import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.CommandResult;
import io.dreamstudio.ordering.common.RpcRequest;
import io.dreamstudio.ordering.common.dispatcher.CommandResultFuture;
import io.dreamstudio.ordering.common.dispatcher.RequestPendingCenter;
import io.dreamstudio.ordering.common.order.OrderCommand;
import io.dreamstudio.ordering.util.IdUtils;
import io.dreamstudio.ordering.util.JsonUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 点餐系统 - 客户端
 * @author Ricky Fung
 */
public class OrderClientV2 {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private String host;
    private int port;

    public OrderClientV2(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    //pipeline
                    ChannelPipeline pipeline = ch.pipeline();

                    //注意：顺序不能错
                    //handler的顺序：读保证自上而下，写保证自下而上就行了，读与写之间其实顺序无所谓，但是一般为了好看对称，我们是一组一组写。
                    pipeline.addLast(new OrderFrameDecoder());
                    pipeline.addLast(new OrderFrameEncoder());

                    pipeline.addLast(new OrderProtocolEncoder());
                    pipeline.addLast(new OrderProtocolDecoder());

                    //处理响应结果
                    pipeline.addLast(new OrderClientHandler());

                    //增加LOG
                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            LOG.info("点餐系统-客户端, 连接服务器:{}:{}", host, port);

            submitTask(f);

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    private void submitTask(ChannelFuture f) throws Exception {

        for (int i=0; i<1000; i++) {
            //1.发送点餐请求
            Long requestId = IdUtils.nextRequestId();
            Command command = new OrderCommand(IdUtils.nextSeatId(), Arrays.asList("鱼香肉丝"));
            RpcRequest request = new RpcRequest(requestId, command);

            //2.写入
            f.channel().writeAndFlush(request);
            LOG.info("点餐系统-客户端, 向服务器:{}:{} 发送点餐请求requestId:{}, 请求报文:{}",
                    host, port, requestId, JsonUtils.toJson(request));

            //3.等待响应结果
            CommandResultFuture future = new CommandResultFuture();
            RequestPendingCenter.INSTANCE.add(requestId, future);

            //3.1 获取结果
            CommandResult result = future.get();
            LOG.info("点餐系统-客户端, 收到服务器:{}:{} 请求requestId:{} 响应结果:{}",
                    host, port, requestId, JsonUtils.toJson(result));
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        String host = "127.0.0.1";
        if (args.length > 1) {
            host = args[1];
        }

        new OrderClientV2(host, port).run();
    }
}
