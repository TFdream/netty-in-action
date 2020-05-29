package io.dreamstudio.ordering.util;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
public class ThreadPoolExecutorFactory {

    public static final ThreadPoolExecutorFactory INSTANCE = new ThreadPoolExecutorFactory();

    private final ThreadPoolExecutor threadPoolExecutor = create(10, 50);

    public ThreadPoolExecutor threadPoolExecutor() {
        return threadPoolExecutor;
    }

    public void execute(Runnable command) {
        threadPoolExecutor.execute(command);
    }

    public Future<?> submit(Runnable command) {
        return threadPoolExecutor.submit(command);
    }

    //---------
    public static ThreadPoolExecutor create() {
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
        return create(corePoolSize, corePoolSize, 5L, TimeUnit.MINUTES);
    }

    public static ThreadPoolExecutor create(int corePoolSize, int maximumPoolSize) {
        return create(corePoolSize, maximumPoolSize, 5L, TimeUnit.MINUTES);
    }

    public static ThreadPoolExecutor create(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                            TimeUnit unit) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, new LinkedBlockingQueue<>(), new DefaultThreadFactory("business"));
    }
}
