package io.dreamstudio.ordering.server.codec;

import io.dreamstudio.ordering.common.RpcResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 自定义协议报文编码器
 * @author Ricky Fung
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<RpcResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcResponse response, List<Object> out) throws Exception {
        //注意：此处必须用ctx.alloc()来分配
        ByteBuf buf = ctx.alloc().buffer();

        //1.编码
        response.encode(buf);

        //2.加入
        out.add(buf);
    }
}
