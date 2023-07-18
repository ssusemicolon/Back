package com.example.demo.controller;

import com.example.demo.dto.BusinessDaysDto;
import com.example.demo.dto.BusinessHoursDto;
import com.example.demo.dto.StoreResponseDto;
import com.example.demo.service.StoreService;
import com.example.demo.vo.StoreInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping(value = "/store/register")
    public StoreResponseDto register(@RequestBody StoreInfoVO storeInfoVo) {
        String storeName = storeInfoVo.getStoreName();
        int seatCount = storeInfoVo.getSeatCount();
        String password = storeInfoVo.getPassword();
        String address = storeInfoVo.getAddress();
        double latitude = storeInfoVo.getLatitude();
        double longitude = storeInfoVo.getLongitude();
        BusinessDaysDto businessDaysDto = storeInfoVo.getBusinessDaysDto();
        BusinessHoursDto businessHoursDto = storeInfoVo.getBusinessHoursDto();

        Long registeredStoreId = storeService.register(storeName, seatCount, password,
                address, latitude, longitude,
                businessDaysDto.toEntity(),
                businessHoursDto.toEntity());

        return new StoreResponseDto(registeredStoreId);
    }
}
