package com.example.seatitssu.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class StoreFindNearByRequestDto {
    // 반경을 저장하는 변수. km 단위
    private double radius;
    private double latitude;
    private double longitude;
}
