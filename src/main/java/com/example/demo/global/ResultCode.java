package com.example.demo.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // 매장 관련 코드
    REGISTER_STORE_SUCCESS("S001", "매장이 새로 등록되었습니다."),
    GET_SPECIFIC_STORE_SUCCESS("S002", "선택하신 매장의 정보를 조회하였습니다."),
    GET_ALL_THE_STORES_SUCCESS("S003", "등록된 모든 매장의 정보를 조회하였습니다."),
    UPDATE_SEAT_COUNT_SUCCESS("S004", "선택하신 매장의 좌석 개수를 변경하였습니다."),
    DELETE_SPECIFIC_STORE_SUCCESS("S005", "선택하신 매장을 삭제하였습니다."),

    // 밀집도 관련 코드
    UPDATE_DENSITY_SUCCESS("D001", "선택한 매장의 밀집도를 갱신하였습니다."),
    GET_RECENT_DENSITY_SUCCESS("D002", "선택한 매장의 현재 밀집도를 조회하였습니다."),

    GET_SPECIFIC_DAY_DENSITY_SUCCESS("D003", "선택한 매장의 특정 날짜의 시간별 밀집도를 조회하였습니다."),
    GET_DAY_DENSITY_SUCCESS("D004", "선택한 매장의 최근 하루 밀집도 통계를 조회하였습니다."),
    GET_WEEK_DENSITY_SUCCESS("D005", "선택한 매장의 최근 일주일 밀집도 통계를 조회하였습니다.");


    private final String code;
    private final String message;
}
