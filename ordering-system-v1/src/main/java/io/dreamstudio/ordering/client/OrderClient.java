package io.dreamstudio.ordering.client;

import io.dreamstudio.ordering.client.codec.OrderFrameDecoder;
import io.dreamstudio.ordering.client.codec.OrderFrameEncoder;
import io.dreamstudio.ordering.client.codec.OrderProtocolDecoder;
import io.dreamstudio.ordering.client.codec.OrderProtocolEncoder;
import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.RpcRequest;
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
public class OrderClient {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private String host;
    private int port;

    public OrderClient(String host, int port) {
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
                    pipeline.addLast(new OrderFrameDecoder());
                    pipeline.addLast(new OrderFrameEncoder());

                    pipeline.addLast(new OrderProtocolEncoder());
                    pipeline.addLast(new OrderProtocolDecoder());

                    //增加LOG
                    pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            LOG.info("点餐系统-客户端, 连接服务器:{}:{}", host, port);

            //1.发送点餐请求
            Command command = new OrderCommand(101, Arrays.asList("鱼香肉丝"));
            RpcRequest request = new RpcRequest(IdUtils.nextRequestId(), command);

            //2.写入
            f.channel().writeAndFlush(request);
            LOG.info("点餐系统-客户端, 向服务器:{}:{} 发送点餐请求:{}", host, port, JsonUtils.toJson(request));

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
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

        new OrderClient(host, port).run();
    }
}
