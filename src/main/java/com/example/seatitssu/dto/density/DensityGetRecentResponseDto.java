package com.example.seatitssu.dto.density;

import com.example.seatitssu.entity.Density;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter @Setter
public class DensityGetRecentResponseDto {

    private Long storeId;

    private int density;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime when;

    public static DensityGetRecentResponseDto of(Density density) {
        return DensityGetRecentResponseDto.builder()
                .storeId(density.getId())
                .density(density.getDensityRate())
                .when(density.getCalculatedTime())
                .build();
    }

}
