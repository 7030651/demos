package com.demo.study.server.handler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;


/**
 * 连接数监测：
 *  - 当有 Channel 连接时，计数 + 1，连接释放时，计数 - 1.
 */
@ChannelHandler.Sharable
 public class MetricsHandler extends ChannelDuplexHandler {
    private AtomicLong totalConnectionNumber = new AtomicLong();
    private MetricRegistry metricRegistry;

    public MetricsHandler() {
        metricRegistry = new MetricRegistry();
        metricRegistry.register("TotalConnectionNumber", new Gauge<Long>() {
            @Override
            public Long getValue() {
                return totalConnectionNumber.get();
            }
        });

        // 控制台输出 metrics 信息。
        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(10, TimeUnit.SECONDS);

        // JMX 输出 metrics 信息。
        JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        totalConnectionNumber.incrementAndGet();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        totalConnectionNumber.decrementAndGet();
        super.channelInactive(ctx);
    }

    
}
