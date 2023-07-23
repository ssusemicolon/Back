package com.example.demo.dto.store;

import com.example.demo.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreFindNearByResponseDto extends StoreInfoResponseDto {
    private double distance;

    public static StoreFindNearByResponseDto of(Store store, double distance) {
        return StoreFindNearByResponseDto.builder()
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
                .distance((double)Math.round(distance * 1000) / 1000)
                .build();
    }
}
