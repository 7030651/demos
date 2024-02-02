package com.demo.study.common.order;

import com.demo.study.common.OperationResult;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderOperationResult extends OperationResult {
    private final int tableId;
    private final String dish;
    private final boolean complete;
}
