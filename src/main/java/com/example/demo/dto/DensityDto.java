package com.example.demo.dto;

import com.example.demo.entity.Density;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class DensityDto {
    private Long storeId;
    private int density;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime when;

    // 이거 하려면 Store 객체를 넣어야 하는데, 그럼 Repository 객체를 써야 하는 거야? 흠..
    public Density toEntity() {
        return null;
    }
}
