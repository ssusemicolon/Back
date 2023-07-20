package com.example.demo.dto.density;

import com.example.demo.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter @Setter
public class DensityGetSpecificDayResponseDto {
    private Long storeId;

    private int openBusinessHour;
    private int closeBusinessHour;

    List<Integer> densityPerHourList;

    public static DensityGetSpecificDayResponseDto of(Store store, List<Integer> densityPerHourList) {
        return DensityGetSpecificDayResponseDto.builder()
                .storeId(store.getId())
                .openBusinessHour(store.getBusinessHours().getOpenBusinessHour())
                .closeBusinessHour(store.getBusinessHours().getCloseBusinessHour())
                .densityPerHourList(densityPerHourList)
                .build();
    }

    public static DensityGetSpecificDayResponseDto of(Long storeId, int openBusinessHour, int closeBusinessHour, List<Integer> densityPerHourList) {
        return DensityGetSpecificDayResponseDto.builder()
                .storeId(storeId)
                .openBusinessHour(openBusinessHour)
                .closeBusinessHour(closeBusinessHour)
                .densityPerHourList(densityPerHourList)
                .build();
    }
}
