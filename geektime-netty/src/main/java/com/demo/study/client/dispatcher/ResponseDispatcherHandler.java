package com.demo.study.client.dispatcher;

import com.demo.study.common.ResponseMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<ResponseMessage> {
    private RequestPendingCenter requestPendingCenter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        requestPendingCenter.set(msg.getMessageHeader().getStreamId(), msg.getMessageBody());        
    }
    
}
