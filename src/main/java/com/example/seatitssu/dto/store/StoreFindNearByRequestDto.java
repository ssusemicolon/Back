package com.example.seatitssu.dto.store;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class StoreFindNearByRequestDto {
    @NotNull(message = "검색할 반경 값은 필수로 입력해야 합니다.")
    private double radius; // 반경을 저장하는 변수. km 단위
    @NotNull(message = "현재 위치를 뜻하는 위도가 필요합니다.")
    private double latitude;
    @NotNull(message = "현재 위치를 뜻하는 경도가 필요합니다.")
    private double longitude;
}
