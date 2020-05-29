package io.dreamstudio.ordering.client.handler;

import io.dreamstudio.ordering.common.RpcResponse;
import io.dreamstudio.ordering.common.dispatcher.RequestPendingCenter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Ricky Fung
 */
public class OrderClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {

        Long requestId = response.getHeader().getRequestId();
        //设置响应结果
        RequestPendingCenter.INSTANCE.set(requestId, response.getBody());
    }
}
