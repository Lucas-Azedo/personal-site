package com.lucasazedo.Exception.Business;

import com.lucasazedo.Exception.BusinessException;

public class InvalidCredentialsException extends BusinessException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
