package com.example.demo.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class StoreDeleteResponseDto {
    private Long storeId;

    public static StoreDeleteResponseDto of(Long storeId) {
        return StoreDeleteResponseDto.builder()
                .storeId(storeId)
                .build();
    }
}
