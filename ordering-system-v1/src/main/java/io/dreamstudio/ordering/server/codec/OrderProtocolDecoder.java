package io.dreamstudio.ordering.server.codec;

import io.dreamstudio.ordering.common.RpcRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 自定义协议报文解码器
 * @author Ricky Fung
 */
public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        RpcRequest request = new RpcRequest();
        request.decode(msg);

        out.add(request);
    }
}
