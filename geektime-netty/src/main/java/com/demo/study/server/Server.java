package com.demo.study.server;

import com.demo.study.common.codec.order.OrderFrameDecoder;
import com.demo.study.common.codec.order.OrderFrameEncoder;
import com.demo.study.common.codec.order.OrderRequestDecoder;
import com.demo.study.common.codec.order.OrderResponseEncoder;
import com.demo.study.server.handler.MetricsHandler;
import com.demo.study.server.handler.ServerProcessHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.flush.FlushConsolidationHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

public class Server {
    public static void main(String[] args) {
        ServerBootstrap boot = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup(0, new DefaultThreadFactory("worker"));
        UnorderedThreadPoolEventExecutor orderThreadPoolExecutor = new UnorderedThreadPoolEventExecutor(10, new DefaultThreadFactory("order"));
        try {
            MetricsHandler metricsHandler = new MetricsHandler();
            boot.channel(NioServerSocketChannel.class)
                    .option(NioChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .group(boss, worker)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline()
                                    .addLast("metricsHandler", metricsHandler)
                                    .addLast(new OrderFrameDecoder())
                                    .addLast(new OrderFrameEncoder())
                                    .addLast(new OrderResponseEncoder())
                                    .addLast(new OrderRequestDecoder())
                                    .addLast(new LoggingHandler(LogLevel.INFO))
                                    .addLast("flushEnhance", new FlushConsolidationHandler(5, true))
                                    .addLast(orderThreadPoolExecutor, new ServerProcessHandler());
                        }
                    });

            ChannelFuture channelFuture = boot.bind(8090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
