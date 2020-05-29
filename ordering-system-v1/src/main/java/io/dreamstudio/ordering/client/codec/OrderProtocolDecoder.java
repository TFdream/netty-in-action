package io.dreamstudio.ordering.client.codec;

import io.dreamstudio.ordering.common.RpcResponse;
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
        RpcResponse response = new RpcResponse();
        response.decode(msg);

        out.add(response);
    }
}
