package com.lucasazedo.Exception.Business;

import com.lucasazedo.Exception.BusinessException;

public class InvalidInputException extends BusinessException {
    public InvalidInputException(String message) {
        super(message);
    }
}
