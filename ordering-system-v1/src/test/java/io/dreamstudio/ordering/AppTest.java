package io.dreamstudio.ordering;

import com.google.common.util.concurrent.Uninterruptibles;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
public class AppTest {

    @Test
    public void testApp() {
        UnorderedThreadPoolEventExecutor threadPoolEventExecutor = new UnorderedThreadPoolEventExecutor(10);
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.HOURS);
    }
}
