package io.dreamstudio.ordering.server.handler;

import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.CommandResult;
import io.dreamstudio.ordering.common.RpcRequest;
import io.dreamstudio.ordering.common.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Ricky Fung
 */
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RpcRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {

        //1.处理业务请求
        Command command = request.getBody();
        CommandResult result = command.execute();

        //2.封装响应
        RpcResponse response = new RpcResponse();
        response.setHeader(request.getHeader());
        response.setBody(result);

        //3.写入
        ctx.writeAndFlush(response);
    }
}
