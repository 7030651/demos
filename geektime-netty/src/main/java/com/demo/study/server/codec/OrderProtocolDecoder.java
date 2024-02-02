package com.demo.study.server.codec;

import java.util.List;

import com.demo.study.common.RequestMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        RequestMessage request = new RequestMessage();
        request.decode(msg);

        out.add(request);
    }
    
    
}
