package com.example.demo.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class StoreDeleteResponseDto { // 멤버가 StoreRegisterResponseDto랑 완전히 똑같음. 합칠지 고민 좀 해봐.
    private Long storeId;

    public static StoreDeleteResponseDto of(Long storeId) {
        return StoreDeleteResponseDto.builder()
                .storeId(storeId)
                .build();
    }
}
