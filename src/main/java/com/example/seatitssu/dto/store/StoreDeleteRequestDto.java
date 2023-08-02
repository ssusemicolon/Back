package com.example.seatitssu.dto.store;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StoreDeleteRequestDto {
    @NotBlank
    @Size(min = 4, max = 20, message = "비밀번호는 4자리 이상, 20자리 이하의 문자열입니다.")
    private String password;
}
