package com.demo.study.common;

import java.nio.charset.Charset;

import com.demo.study.common.util.JsonUtil;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public abstract class Message<T extends MessageBody> {

    private MessageHeader messageHeader;
    private T messageBody;
    
    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());
    }

    public abstract Class<T> getMessageBodyDecodeClass(int opCode);

    public void decode(ByteBuf msg) {
        int version = msg.readInt();
        long streamId = msg.readLong();
        int opCode = msg.readInt();
        this.messageHeader = new MessageHeader(version, opCode, streamId);

        Class<T> bodyClass = getMessageBodyDecodeClass(opCode);
        this.messageBody = JsonUtil.fromJson(msg.toString(Charset.forName("UTF-8")), bodyClass);
    }
}
