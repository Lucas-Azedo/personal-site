package com.lucasazedo.Exception.Business;

import com.lucasazedo.Exception.BusinessException;

public class InvalidInput extends BusinessException {
    public InvalidInput(String message) {
        super(message);
    }
}
