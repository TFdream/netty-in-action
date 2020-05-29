package io.dreamstudio.ordering.server.handler;

import io.dreamstudio.ordering.common.Command;
import io.dreamstudio.ordering.common.CommandResult;
import io.dreamstudio.ordering.common.RpcRequest;
import io.dreamstudio.ordering.common.RpcResponse;
import io.dreamstudio.ordering.util.JsonUtils;
import io.dreamstudio.ordering.util.ThreadPoolExecutorFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ricky Fung
 */
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {

        LOG.info("点餐系统-服务端, 接收到请求:{}", JsonUtils.toJson(request));

        //优化点：判断是 CPU密集型 还是 IO密集型
        //如果是IO密集型 需要在使用单独的业务线程池来执行，避免阻塞IO线程
        ThreadPoolExecutorFactory.INSTANCE.threadPoolExecutor().execute(()->{
            //1.处理业务请求
            Command command = request.getBody();
            CommandResult result = command.execute();

            //2.封装响应
            RpcResponse response = new RpcResponse();
            response.setHeader(request.getHeader());
            response.setBody(result);

            //3.写入
            ctx.writeAndFlush(response);
        });
    }
}
