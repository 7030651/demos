package com.demo.study.common.keepalive;

import com.demo.study.common.OperationResult;

import lombok.Data;

@Data
public class KeepaliveOperationResult extends OperationResult {
    private final long time;
}
