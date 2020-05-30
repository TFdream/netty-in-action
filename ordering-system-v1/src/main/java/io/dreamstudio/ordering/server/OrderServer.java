package io.dreamstudio.ordering.server;

import io.dreamstudio.ordering.server.codec.OrderFrameDecoder;
import io.dreamstudio.ordering.server.codec.OrderFrameEncoder;
import io.dreamstudio.ordering.server.codec.OrderProtocolDecoder;
import io.dreamstudio.ordering.server.codec.OrderProtocolEncoder;
import io.dreamstudio.ordering.server.handler.OrderServerProcessHandler;
import io.dreamstudio.ordering.server.handler.ServerIdleCheckHandler;
import io.dreamstudio.ordering.server.handler.UserAuthHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 点单系统 - 服务端
 * 参考示例: https://netty.io/wiki/user-guide-for-4.x.html
 * @author Ricky Fung
 */
public class OrderServer {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private int port;

    public OrderServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        UserAuthHandler userAuthHandler = new UserAuthHandler();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .handler(new LoggingHandler(LogLevel.INFO)) //增加LOG
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() { // (4)
                        @Override
                        public void initChannel(NioSocketChannel ch) throws Exception {
                            //pipeline
                            ChannelPipeline pipeline = ch.pipeline();

                            //增加LOG
                            pipeline.addLast("loggingHandler", new LoggingHandler(LogLevel.INFO));

                            //空闲检测
                            pipeline.addLast("idleCheckHandler", new ServerIdleCheckHandler());

                            //注意：顺序不能错
                            //handler的顺序：读保证自上而下，写保证自下而上就行了，读与写之间其实顺序无所谓，但是一般为了好看对称，我们是一组一组写。
                            pipeline.addLast("orderFrameDecoder", new OrderFrameDecoder());
                            pipeline.addLast("orderFrameEncoder", new OrderFrameEncoder());

                            pipeline.addLast("orderProtocolEncoder", new OrderProtocolEncoder());
                            pipeline.addLast("orderProtocolDecoder", new OrderProtocolDecoder());

                            //用户身份鉴权
                            pipeline.addLast("userAuthHandler", userAuthHandler);

                            pipeline.addLast("orderProcessHandler", new OrderServerProcessHandler());
                        }
                    });

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)
            LOG.info("点餐系统-服务端, port:{} 启动完成", port);

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new OrderServer(port).run();
    }
}
