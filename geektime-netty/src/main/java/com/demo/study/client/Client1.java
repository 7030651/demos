package com.demo.study.client;

import com.demo.study.common.RequestMessage;
import com.demo.study.common.codec.order.OrderFrameDecoder;
import com.demo.study.common.codec.order.OrderFrameEncoder;
import com.demo.study.common.codec.order.OrderRequestEncoder;
import com.demo.study.common.codec.order.OrderResponseDecoder;
import com.demo.study.common.order.OrderOperation;
import com.demo.study.common.util.IdUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Client1 {
    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            boot.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new OrderFrameDecoder())
                            .addLast(new OrderFrameEncoder())
                            .addLast(new OrderRequestEncoder())
                            .addLast(new OrderResponseDecoder())
                            .addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });

            ChannelFuture channelFuture = boot.connect("127.0.0.1", 8090);
            channelFuture.sync();

            RequestMessage request = new RequestMessage(IdUtil.nextId(), new OrderOperation(1001,"potato"));
            channelFuture.channel().writeAndFlush(request);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
