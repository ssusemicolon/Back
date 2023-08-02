package com.example.seatitssu.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    TEST_ERROR(500, "E001", "테스트 에러입니다."),
    STORE_NOT_FOUND(404, "E001", "해당 storeId를 가진 매장이 존재하지 않습니다."),
    STORE_FALSE_PASSWORD(401, "E002", "매장의 비밀번호가 일치하지 않습니다."),
    DENSITY_NOT_FOUND(404, "E003", "밀집도 값이 존재하지 않습니다.");

    private final int status;
    private final String code;
    private final String message;
}
