package com.demo.study.common;

import java.util.function.Predicate;

import com.demo.study.common.auth.AuthOperation;
import com.demo.study.common.auth.AuthOperationResult;
import com.demo.study.common.keepalive.KeepaliveOperation;
import com.demo.study.common.keepalive.KeepaliveOperationResult;
import com.demo.study.common.order.OrderOperation;
import com.demo.study.common.order.OrderOperationResult;

public enum OperationType {
 

    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    KEEPALIVE(2, KeepaliveOperation.class, KeepaliveOperationResult.class),
    ORDER(3, OrderOperation.class, OrderOperationResult.class);

    private int opCode;
    private Class<? extends Operation> operationClass;
    private Class<? extends OperationResult> resultClass;
  

    OperationType(int opCode, Class<? extends Operation> operationClass, Class<? extends OperationResult> resultClass) {
        this.opCode = opCode;
        this.operationClass = operationClass;
        this.resultClass = resultClass;
    }

    public int getOpCode() {
        return opCode;
    }
    public Class<? extends Operation> getOperationClass() {
        return operationClass;
    }
    public Class<? extends OperationResult> getResultClass() {
        return resultClass;
    }

    public static OperationType fromOpCode(int opCode) {
        return getOperationType(type -> type.opCode == opCode);
    }

    public static OperationType fromOperation(Operation operation) {
        return getOperationType(type -> type.operationClass == operation.getClass());
    }

    public static OperationType getOperationType(Predicate<OperationType> predicate) {
        OperationType[] values = values();
        for (OperationType type: values) {
            if (predicate.test(type)) {
                return type;
            }
        }
        throw new AssertionError("type no found.");
    }
}
