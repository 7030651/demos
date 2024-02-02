package com.demo.study.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageHeader {
    private int version = 1;
    private int opCode;
    private long streamId;
}
