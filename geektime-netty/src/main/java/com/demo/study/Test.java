package com.demo.study;

import com.demo.study.common.OperationType;

import lombok.Data;

@Data
public class Test {
    
    private Integer flag;

    public static void main(String[] args) {
        Test t = new Test();
        t.setFlag(2);        
        System.out.println(t.getFlag());

        System.out.println(OperationType.AUTH.getOpCode() + ", " + OperationType.AUTH.getOperationClass().getSimpleName());
    }

}
