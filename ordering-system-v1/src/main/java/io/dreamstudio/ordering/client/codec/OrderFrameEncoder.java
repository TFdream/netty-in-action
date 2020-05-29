package io.dreamstudio.ordering.client.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 响应报文桢
 * @author Ricky Fung
 */
public class OrderFrameEncoder extends LengthFieldPrepender {

    public OrderFrameEncoder() {
        super(2);
    }
}
