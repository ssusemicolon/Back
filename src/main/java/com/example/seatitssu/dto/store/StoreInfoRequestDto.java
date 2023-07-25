package com.example.seatitssu.dto.store;

import com.example.seatitssu.entity.BusinessDays;
import com.example.seatitssu.entity.BusinessHours;
import com.example.seatitssu.entity.Day;
import com.example.seatitssu.entity.Store;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreInfoRequestDto {
    private String storeName;
    private String thumUrl;
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
                storeName, thumUrl, seatCount, password,
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
