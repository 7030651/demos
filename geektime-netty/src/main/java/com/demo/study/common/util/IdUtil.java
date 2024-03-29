package com.demo.study.common.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdUtil {
    private static final AtomicLong IDX = new AtomicLong();
    
    public static long nextId() {
        return IDX.incrementAndGet();
    }
}
