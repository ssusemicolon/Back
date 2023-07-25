package com.example.seatitssu.dto.store;

import com.example.seatitssu.entity.Day;
import com.example.seatitssu.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

// @Builder
@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfoResponseDto {
    private Long storeId;
    private String storeName;
    private String thumUrl;
    private int seatCount;
    private String address;
    private double latitude;
    private double longitude;
    private int openBusinessHour;
    private int closeBusinessHour;
    private List<Day> businessDays;

    public static StoreInfoResponseDto of(Store store) {
        return StoreInfoResponseDto.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .thumUrl(store.getThumUrl())
                .seatCount(store.getSeatCount())
                .address(store.getAddress())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .openBusinessHour(store.getBusinessHours().getOpenBusinessHour())
                .closeBusinessHour(store.getBusinessHours().getCloseBusinessHour())
                .businessDays(store.getBusinessDays().getBusinessDayList())
                .build();
    }
}