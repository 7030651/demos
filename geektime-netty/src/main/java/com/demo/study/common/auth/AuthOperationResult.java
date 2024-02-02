package com.demo.study.common.auth;

import com.demo.study.common.OperationResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthOperationResult extends OperationResult {
    private final boolean passAuth; 
}
