package com.example.seatitssu.global.error;

import com.example.seatitssu.global.result.ResultCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ErrorResponse {
    // 질문: HttpStatus 이용해서 어차피 status code 집어넣는데 왜 필요하지?
    // private int status;
    private String code;
    private String message;

    public ErrorResponse(final ErrorCode errorCode) {
        // this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ErrorResponse of(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(final String code, final String message) {
        return new ErrorResponse(code, message);
    }
}