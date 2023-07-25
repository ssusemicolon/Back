package com.example.seatitssu.controller;

import com.example.seatitssu.dto.density.DensityGetRecentResponseDto;
import com.example.seatitssu.dto.density.DensityGetSpecificDayResponseDto;
import com.example.seatitssu.dto.density.DensityGetWeekResponseDto;
import com.example.seatitssu.dto.density.DensityUpdateRequestDto;
import com.example.seatitssu.global.ResultCode;
import com.example.seatitssu.global.ResultResponse;
import com.example.seatitssu.service.DensityService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class DensityController {
    private final DensityService densityService;
    @PostMapping("/density/updateDensity")
    public ResultResponse updateDensity(@RequestBody DensityUpdateRequestDto densityUpdateRequestDto) {
        densityService.updateDensity(densityUpdateRequestDto);
        return ResultResponse.of(ResultCode.UPDATE_DENSITY_SUCCESS);
    }

    @GetMapping("/density/current/{storeId}")
    public ResultResponse getRecentDensity(@PathVariable("storeId") Long storeId) {
        DensityGetRecentResponseDto densityGetRecentResponseDto =  densityService.getRecentDensity(storeId);
        return ResultResponse.of(ResultCode.GET_RECENT_DENSITY_SUCCESS, densityGetRecentResponseDto);
    }

    @GetMapping("/density/{storeId}")
    public ResultResponse getSpecificDayDensity(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate specificDate,
                                                @PathVariable("storeId") Long storeId) {
        DensityGetSpecificDayResponseDto densityGetSpecificDayResponseDto = densityService.getSpecificDayDensity(storeId, specificDate);
        return ResultResponse.of(ResultCode.GET_SPECIFIC_DAY_DENSITY_SUCCESS, densityGetSpecificDayResponseDto);
    }

    @GetMapping("/density/week/{storeId}")
    public ResultResponse getWeekDensity(@PathVariable("storeId") Long storeId) {
        // 클라이언트 요청 시간이 자정 직전일 경우, 날짜가 넘어가는 문제가 생길 수 있어서.. 프론트 단에서 날짜를 넘겨받을지 고민 중
        LocalDate today = LocalDate.now();
        DensityGetWeekResponseDto densityGetWeekResponseDto = densityService.getWeekDensity(storeId, today);
        return ResultResponse.of(ResultCode.GET_WEEK_DENSITY_SUCCESS, densityGetWeekResponseDto);
    }
}
