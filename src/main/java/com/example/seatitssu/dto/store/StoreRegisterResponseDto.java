package com.example.seatitssu.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterResponseDto {
    private Long storeId;

    public static StoreRegisterResponseDto of(Long storeId) {
        return StoreRegisterResponseDto.builder()
                .storeId(storeId)
                .build();
    }
}
