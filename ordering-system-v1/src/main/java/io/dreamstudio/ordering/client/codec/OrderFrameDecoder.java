package io.dreamstudio.ordering.client.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 请求报文桢解码器
 * @author Ricky Fung
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {
    public OrderFrameDecoder() {
        this(Integer.MAX_VALUE);
    }

    public OrderFrameDecoder(int maxFrameLength) {
        super(maxFrameLength, 0, 2, 0, 2);
    }
}
