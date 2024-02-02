package com.demo.study.common.codec.order;

import java.util.List;

import com.demo.study.common.ResponseMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class OrderResponseDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        ResponseMessage response = new ResponseMessage();
        response.decode(msg);

        out.add(response);
    }
    
}
