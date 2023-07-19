package com.example.demo.dto;

import com.example.demo.entity.BusinessDays;
import com.example.demo.entity.BusinessHours;
import com.example.demo.entity.Day;
import com.example.demo.entity.Store;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreInfoRequestDto {
    private String storeName;
    private String imageUrl;
    private int seatCount;
    private String password;
    private String address;
    private double latitude;
    private double longitude;

    private int openBusinessHour;
    private int closeBusinessHour;

    private List<Day> businessDays;

    public Store toEntity() {
        return new Store(
                storeName, imageUrl, seatCount, password,
                address, latitude, longitude,
                getBusinessDays(), getBusinessHours());
    }

    public BusinessDays getBusinessDays() {
        return new BusinessDays(businessDays);
    }

    private BusinessHours getBusinessHours() {
        return new BusinessHours(openBusinessHour, closeBusinessHour);
    }

}
