package com.example.seatitssu.dto.density;

import com.example.seatitssu.entity.Day;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class DensityGetWeekResponseDto {
    private Long storeId;
    private List<Day> businessDays;
    private List<Integer> densityPerDayList;

    public static DensityGetWeekResponseDto of(Long storeId, List<Day> businessDays, List<Integer> densityPerDayList) {
        return DensityGetWeekResponseDto.builder()
                .storeId(storeId)
                .businessDays(businessDays)
                .densityPerDayList(densityPerDayList)
                .build();
    }
}
