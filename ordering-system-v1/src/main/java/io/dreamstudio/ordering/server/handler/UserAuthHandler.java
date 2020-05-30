package io.dreamstudio.ordering.server.handler;

import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.RpcRequest;
import io.dreamstudio.ordering.common.auth.AuthCommand;
import io.dreamstudio.ordering.common.auth.AuthCommandResult;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户身份鉴权
 * @author Ricky Fung
 */
@ChannelHandler.Sharable
public class UserAuthHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {

        try {
            //注意：第一个请求报文 必须是用户授权
            Command command = request.getBody();
            if (command instanceof AuthCommand) {
                //授权
                AuthCommand authCommand = (AuthCommand) command;
                AuthCommandResult result = authCommand.execute();
                if (result.isPassAuth()) {
                    LOG.info("点餐系统-服务端, 用户身份认证通过");
                    //可以移除当前handler了
                } else {
                    LOG.error("点餐系统-服务端, 用户身份认证失败，关闭连接");
                    ctx.close();
                }
            } else {
                LOG.error("点餐系统-服务端, 第一个请求报文必须是授权");
                //用户没做授权，直接关闭连接
                ctx.close();
            }
        } catch (Exception e) {
            LOG.error("点餐系统-服务端, 用户身份认证异常", e);
        } finally {
            //移除授权handler
            ctx.pipeline().remove(this);
        }
    }
}
