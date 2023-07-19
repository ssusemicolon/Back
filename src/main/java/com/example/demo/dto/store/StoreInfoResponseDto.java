package com.example.demo.dto.store;

import com.example.demo.entity.Day;
import com.example.demo.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfoResponseDto {
    private Long storeId;
    private String storeName;
    private String imageUrl;
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
                .imageUrl(store.getImageUrl())
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
