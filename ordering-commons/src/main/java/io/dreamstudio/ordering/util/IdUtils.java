package io.dreamstudio.ordering.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 生成ID工具类
 * @author Ricky Fung
 */
public abstract class IdUtils {

    private static final AtomicLong REQUEST_COUNTER = new AtomicLong(1);
    private static final AtomicLong ORDER_COUNTER = new AtomicLong(1);
    private static final AtomicLong SEAT_COUNTER = new AtomicLong(1);

    /**
     * 生成座位ID
     * @return
     */
    public static long nextSeatId() {
        return SEAT_COUNTER.getAndIncrement();
    }

    /**
     * 生成订单ID
     * @return
     */
    public static long nextOrderId() {
        return ORDER_COUNTER.getAndIncrement();
    }

    /**
     * 生成请求ID
     * @return
     */
    public static long nextRequestId() {
        return REQUEST_COUNTER.getAndIncrement();
    }
}
