package com.demo.study.common.auth;

import com.demo.study.common.Operation;
import lombok.Data;


@Data
public class AuthOperation extends Operation {
    private final String username;
    private final String password;

    @Override
    public AuthOperationResult execute() {
        if("admin".equalsIgnoreCase(this.username)){
            AuthOperationResult orderResponse = new AuthOperationResult(true);
            return orderResponse;
        }

        return new AuthOperationResult(false);
    } 
}
