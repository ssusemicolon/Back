package com.example.seatitssu.dto.store;

import com.example.seatitssu.entity.BusinessDays;
import com.example.seatitssu.entity.BusinessHours;
import com.example.seatitssu.entity.Day;
import com.example.seatitssu.entity.Store;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoreInfoRequestDto {
    @NotBlank(message = "매장명은 필수로 입력해야 합니다.")
    private String storeName;
    @NotBlank(message = "주소는 필수로 입력해야 합니다.")
    private String address;
    private String thumUrl;
    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4자리 이상, 20자리 이하의 문자열이어야 합니다.")
    private String password;
    @NotNull(message = "매자의 좌석개수는 필수로 입력해야 합니다.")
    @Positive(message = "매장의 좌석개수는 1개 이상어야 합니다.")
    private int seatCount;
    @NotNull(message = "매장의 위치를 나타내는 위도는 필수로 입력해야 합니다.")
    private double latitude;
    @NotNull(message = "매장의 위치를 나타내는 경도는 필수로 입력해야 합니다.")
    private double longitude;

    @Size(min = 7, max = 7, message = "요일별 영업 여부는 필수로 선택해야 합니다.")
    private List<Day> businessDays;

    @NotNull(message = "영업 시작 시간은 필수로 입력해야 합니다.")
    @Min(value = 0, message = "영업 시작 시간은 0시부터 24시 사이 값을 입력해야 합니다. 24시 영업인 경우 시작 시간과 종료 시간 모두 24를 입력해 주세요.")
    @Max(value = 24, message = "영업 시작 시간은 0시부터 24시 사이 값을 입력해야 합니다. 24시 영업인 경우 시작 시간과 종료 시간 모두 24를 입력해 주세요.")
    private int openBusinessHour;

    @NotNull(message = "영업 시작 시간은 필수로 입력해야 합니다.")
    @Min(value = 0, message = "영업 종료 시간은 0시부터 24시 사이 값을 입력해야 합니다. 24시 영업인 경우 시작 시간과 종료 시간 모두 24를 입력해 주세요.")
    @Max(value = 24, message = "영업 종료 시간은 0시부터 24시 사이 값을 입력해야 합니다. 24시 영업인 경우 시작 시간과 종료 시간 모두 24를 입력해 주세요.")
    private int closeBusinessHour;

    public Store toEntity() {
        return new Store(
                storeName, thumUrl, seatCount, password,
                address, latitude, longitude,
                getBusinessDays(), getBusinessHours());
    }

    private BusinessDays getBusinessDays() {
        return new BusinessDays(businessDays);
    }

    private BusinessHours getBusinessHours() {
        return new BusinessHours(openBusinessHour, closeBusinessHour);
    }

}
