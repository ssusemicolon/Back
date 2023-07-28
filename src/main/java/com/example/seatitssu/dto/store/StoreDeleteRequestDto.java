package com.example.seatitssu.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StoreDeleteRequestDto {
    @NotBlank
    private String password;
}
