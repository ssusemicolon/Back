package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoreResponseDto extends ResponseDto {
    public StoreResponseDto(Long storeId) {
        super("R001", "새 매장이 등록되었습니다.");
        this.storeId = storeId;
    }
    private final Long storeId;
}
