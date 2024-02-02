package com.demo.study.common.order;

import java.util.concurrent.TimeUnit;

import com.demo.study.common.Operation;
import com.demo.study.common.OperationResult;
import com.google.common.util.concurrent.Uninterruptibles;

import lombok.AllArgsConstructor;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@ToString
public class OrderOperation extends Operation {
    private int tableId;
    private String dish;

    @Override
    public OperationResult execute() {
        log.info("order's executing startup with orderRequest: " + toString());
        //execute order logic
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        log.info("order's executing complete");
        OrderOperationResult orderResponse = new OrderOperationResult(tableId, dish, true);
        return orderResponse;
    }
    
}
