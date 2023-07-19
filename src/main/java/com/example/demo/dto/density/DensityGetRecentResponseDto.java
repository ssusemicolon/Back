package com.example.demo.dto.density;

import com.example.demo.entity.Density;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
