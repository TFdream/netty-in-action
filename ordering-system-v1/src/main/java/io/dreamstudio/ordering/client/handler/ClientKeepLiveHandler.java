package io.dreamstudio.ordering.client.handler;

import io.dreamstudio.ordering.common.RpcRequest;
import io.dreamstudio.ordering.common.keepalive.KeepAliveCommand;
import io.dreamstudio.ordering.util.IdUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端 - 空闲检测
 * @author Ricky Fung
 */
public class ClientKeepLiveHandler extends ChannelInboundHandlerAdapter {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event == IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT) {
                LOG.info("点餐系统-客户端, 检测客户端空闲，主动向服务器发送心跳包");
                //发送心跳包
                KeepAliveCommand command = new KeepAliveCommand();
                RpcRequest request = new RpcRequest(IdUtils.nextRequestId(), command);
                ctx.writeAndFlush(request);
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
