package com.example.seatitssu.dto.density;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class DensityUpdateRequestDto {
    @NotNull(message = "밀집도를 갱신할 매장의 고유 번호가 필요합니다.")
    private Long storeId;

    @NotNull(message = "탐지한 사람의 수가 필요합니다.")
    private int peopleCount;

    @NotNull(message = "밀집도가 갱신된 시간을 표시하기 위해 갱신 시간이 필요합니다.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime when;
}
