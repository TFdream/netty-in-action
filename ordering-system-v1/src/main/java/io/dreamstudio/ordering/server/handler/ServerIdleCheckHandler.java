package io.dreamstudio.ordering.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
public class ServerIdleCheckHandler extends IdleStateHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public ServerIdleCheckHandler() {
        super(30, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent event) throws Exception {
        if (event == IdleStateEvent.FIRST_READER_IDLE_STATE_EVENT) {
            LOG.info("点餐系统-服务端, 检测到客户端空闲，关闭连接");
            ctx.close();
            return;
        }
        super.channelIdle(ctx, event);
    }
}
