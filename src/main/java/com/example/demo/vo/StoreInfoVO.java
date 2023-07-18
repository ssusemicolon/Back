package com.example.demo.vo;

import com.example.demo.dto.BusinessDaysDto;
import com.example.demo.dto.BusinessHoursDto;
import com.example.demo.entity.BusinessDays;
import com.example.demo.entity.BusinessHours;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreInfoVO {
    private String storeName;
    private int seatCount;
    private String password;
    private String address;
    private double latitude;
    private double longitude;

    private int openBusinessHour;
    private int closeBusinessHour;

    private List<Integer> businessDays;

    // 추후 질문
    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    // 추후 질문
    @Override
    public int hashCode() {
        return 1;
    }

    public BusinessDaysDto getBusinessDaysDto() {
        return new BusinessDaysDto(businessDays);
    }

    public BusinessHoursDto getBusinessHoursDto() {
        return new BusinessHoursDto(openBusinessHour, closeBusinessHour);
    }

}
