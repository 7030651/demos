package com.demo.study.client;

import java.util.concurrent.ExecutionException;

import com.demo.study.client.dispatcher.OperationResultFuture;
import com.demo.study.client.dispatcher.RequestPendingCenter;
import com.demo.study.client.dispatcher.ResponseDispatcherHandler;
import com.demo.study.common.OperationResult;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientV2 {
    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            RequestPendingCenter requestPendingCenter = new RequestPendingCenter();
            boot.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new OrderFrameDecoder())
                            .addLast(new OrderFrameEncoder())
                            .addLast(new OrderRequestEncoder())
                            .addLast(new OrderResponseDecoder())
                            .addLast(new ResponseDispatcherHandler(requestPendingCenter))
                            .addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });

            ChannelFuture channelFuture = boot.connect("127.0.0.1", 8090);
            channelFuture.sync();

            Long streamId = IdUtil.nextId(); 
            RequestMessage request = new RequestMessage(streamId, new OrderOperation(1001,"potato"));
            // 发送消息前，先添加 Future，使发送消息后可以阻塞获得 Result。
            OperationResultFuture operationResultFuture = new OperationResultFuture();
            requestPendingCenter.add(streamId, operationResultFuture);
            
            for (int i = 0; i < 6; i++) {
                channelFuture.channel().writeAndFlush(request);
            }

            // 获取 Result.
            OperationResult operationResult = operationResultFuture.get();
            log.info("result: " + operationResult);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
