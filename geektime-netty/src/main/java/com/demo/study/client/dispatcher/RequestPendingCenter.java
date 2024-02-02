package com.demo.study.client.dispatcher;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.demo.study.common.OperationResult;

public class RequestPendingCenter {
    private Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add(Long streamId, OperationResultFuture future) {
        this.map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult result) {
        OperationResultFuture future = this.map.get(streamId);
        if (future != null) {
            future.setSuccess(result);
            this.map.remove(streamId);
        }
    }
}
