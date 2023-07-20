package com.example.demo.controller;

import com.example.demo.dto.density.DensityGetRecentResponseDto;
import com.example.demo.dto.density.DensityGetSpecificDayResponseDto;
import com.example.demo.dto.density.DensityUpdateRequestDto;
import com.example.demo.global.ResultCode;
import com.example.demo.global.ResultResponse;
import com.example.demo.service.DensityService;
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
}
