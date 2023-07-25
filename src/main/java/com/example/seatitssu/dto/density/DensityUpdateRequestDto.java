package com.example.seatitssu.dto.density;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class DensityUpdateRequestDto {
    private Long storeId;
    private int density;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime when;

    // 이거 하려면, Density 엔티티의 멤버 객체인 Store store가 있는데,
    // 이걸 넣어주기 위해 Dto 클래스에서 Repository 객체를 써야 하는 거야? 흠..
    /*
    public Density toEntity() {
        return null;
    }
    */
}
