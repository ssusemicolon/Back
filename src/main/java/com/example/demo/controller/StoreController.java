package com.example.demo.controller;

import com.example.demo.dto.StoreInfoRequestDto;
import com.example.demo.dto.StoreInfoResponseDto;
import com.example.demo.global.ResultCode;
import com.example.demo.global.ResultResponse;
import com.example.demo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping(value = "/store/register")
    public ResultResponse register(@RequestBody StoreInfoRequestDto storeInfoRequestDto) {
        Long registeredStoreId = storeService.register(storeInfoRequestDto.toEntity());
        return ResultResponse.of(ResultCode.REGISTER_STORE_SUCCESS, registeredStoreId);
    }

    @GetMapping(value = "/store/storeInfo/{storeId}")
    public ResultResponse findStore(@PathVariable("storeId")Long storeId) {
        StoreInfoResponseDto storeInfoResponseDto = storeService.findStore(storeId);
        return ResultResponse.of(ResultCode.GET_SPECIFIC_STORE_SUCCESS, storeInfoResponseDto);
    }
}
