package com.demo.study.common.keepalive;

import com.demo.study.common.Operation;
import com.demo.study.common.OperationResult;

public class KeepaliveOperation extends Operation {
    private long time;

    public KeepaliveOperation(long time) {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        KeepaliveOperationResult result = new KeepaliveOperationResult(time);        
        return result;
    }
}
