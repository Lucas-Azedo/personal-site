package com.lucasazedo.Exception.Business;

import com.lucasazedo.Exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
