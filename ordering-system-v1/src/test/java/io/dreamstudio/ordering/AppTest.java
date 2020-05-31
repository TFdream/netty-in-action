package io.dreamstudio.ordering;

import com.google.common.util.concurrent.Uninterruptibles;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.IpSubnetFilterRule;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
public class AppTest {

    @Test
    public void testSsl() throws Exception {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        System.out.println(ssc.certificate());
        System.out.println(ssc.privateKey());

        SslContext sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey())
                .build();

        sslContext.newHandler(ByteBufAllocator.DEFAULT);
    }

    @Test
    public void testApp() {
        UnorderedThreadPoolEventExecutor threadPoolEventExecutor = new UnorderedThreadPoolEventExecutor(10);
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.HOURS);

        IpSubnetFilterRule filterRule = new IpSubnetFilterRule("127.0.0.1", 16, IpFilterRuleType.REJECT);
        RuleBasedIpFilter ruleBasedIpFilter = new RuleBasedIpFilter(filterRule);
        System.out.println(ruleBasedIpFilter);

    }
}
