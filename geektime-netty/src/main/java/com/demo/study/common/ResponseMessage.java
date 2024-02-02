package com.demo.study.common;

public class ResponseMessage extends Message<OperationResult> {

    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpCode(opCode).getResultClass();
    }
    
}
