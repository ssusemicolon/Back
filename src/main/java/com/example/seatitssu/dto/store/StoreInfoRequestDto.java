package com.example.seatitssu.dto.store;

import com.example.seatitssu.entity.BusinessDays;
import com.example.seatitssu.entity.BusinessHours;
import com.example.seatitssu.entity.Day;
import com.example.seatitssu.entity.Store;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private int seatCount;
    @NotNull(message = "매장의 위치를 나타내는 위도는 필수로 입력해야 합니다.")
    private double latitude;
    @NotNull(message = "매장의 위치를 나타내는 경도는 필수로 입력해야 합니다.")
    private double longitude;
    private List<Day> businessDays;
    private int openBusinessHour;
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
