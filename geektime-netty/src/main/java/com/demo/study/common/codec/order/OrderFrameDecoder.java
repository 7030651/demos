package com.demo.study.common.codec.order;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder{

    public OrderFrameDecoder() {
        super(65535, 0, 2, 0, 2);
    }
}
