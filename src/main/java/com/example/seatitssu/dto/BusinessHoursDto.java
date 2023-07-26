package com.example.seatitssu.dto;

import com.example.seatitssu.entity.BusinessHours;
import javax.validation.constraints.NotNull;

public class BusinessHoursDto {
    public BusinessHoursDto(int openBusinessHour, int closeBusinessHour) {
        this.openBusinessHour = openBusinessHour;
        this.closeBusinessHour = closeBusinessHour;
    }
    private Long id;
    @NotNull(message = "영업 시작 시간을 입력해주세요!")
    private int openBusinessHour;

    @NotNull(message = "영업 종료 시간을 입력해주세요!")
    private int closeBusinessHour;

    public BusinessHours toEntity() {
        return BusinessHours.builder()
                .openBusinessHour(this.openBusinessHour)
                .closeBusinessHour(this.closeBusinessHour)
                .build();
    }
}
