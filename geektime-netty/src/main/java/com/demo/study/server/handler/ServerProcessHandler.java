package com.demo.study.server.handler;

import com.demo.study.common.OperationResult;
import com.demo.study.common.RequestMessage;
import com.demo.study.common.ResponseMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {
        OperationResult result = msg.getMessageBody().execute();

        ResponseMessage response = new ResponseMessage();
        response.setMessageHeader(msg.getMessageHeader());
        response.setMessageBody(result);

        ctx.writeAndFlush(response);
    }
}
