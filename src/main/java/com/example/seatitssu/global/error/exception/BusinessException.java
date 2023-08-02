package com.example.seatitssu.global.error.exception;

import com.example.seatitssu.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

//    public BusinessException(ErrorCode errorCode, List<ErrorResponse.FieldError> errors) {
//        super(errorCode.getMessage());
//        this.errors = errors;
//        this.errorCode = errorCode;
//    }
}
