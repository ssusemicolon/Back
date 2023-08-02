package com.example.seatitssu.global.error.exception;

import com.example.seatitssu.global.error.ErrorCode;

public class PasswordFalseException extends BusinessException {
    public PasswordFalseException(ErrorCode errorCode) {
        super(errorCode);
    }
}
