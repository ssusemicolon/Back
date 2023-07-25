package com.example.seatitssu.controller;

import com.example.seatitssu.dto.store.StoreDeleteResponseDto;
import com.example.seatitssu.dto.store.StoreFindNearByRequestDto;
import com.example.seatitssu.dto.store.StoreFindNearByResponseDto;
import com.example.seatitssu.dto.store.StoreInfoRequestDto;
import com.example.seatitssu.dto.store.StoreInfoResponseDto;
import com.example.seatitssu.dto.store.StoreRegisterResponseDto;
import com.example.seatitssu.global.ResultCode;
import com.example.seatitssu.global.ResultResponse;
import com.example.seatitssu.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping(value = "/store/register")
    public ResultResponse register(@RequestBody StoreInfoRequestDto storeInfoRequestDto) {
        Long registeredStoreId = storeService.register(storeInfoRequestDto.toEntity());
        return ResultResponse.of(ResultCode.REGISTER_STORE_SUCCESS, StoreRegisterResponseDto.of(registeredStoreId));
    }

    @PutMapping(value = "/store/storeInfo/{storeId}")
    public ResultResponse updateStore(@PathVariable("storeId") Long storeId, @RequestBody StoreInfoRequestDto storeInfoRequestDto) {
        Long registeredStoreId = storeService.updateStore(storeId, storeInfoRequestDto.toEntity());
        return ResultResponse.of(ResultCode.UPDATE_STORE_INFO_SUCCESS, StoreRegisterResponseDto.of(registeredStoreId));
    }

    @GetMapping(value = "/store/storeInfo/{storeId}")
    public ResultResponse findStore(@PathVariable("storeId")Long storeId) {
        StoreInfoResponseDto storeInfoResponseDto = storeService.findStore(storeId);
        return ResultResponse.of(ResultCode.GET_SPECIFIC_STORE_SUCCESS, storeInfoResponseDto);
    }

    @PostMapping(value = "/store/nearby")
    public ResultResponse findNearByStores(@RequestBody StoreFindNearByRequestDto storeFindNearByRequestDto) {
        List<StoreFindNearByResponseDto> storeFindNearByResponseDtoList = storeService.findNearByStores(storeFindNearByRequestDto);
        return ResultResponse.of(ResultCode.SEARCH_STORES_SUCCESS, storeFindNearByResponseDtoList);
    }

    @GetMapping(value = "/store/storeInfo/all")
    public ResultResponse findAllStores() {
        List<StoreInfoResponseDto> storeInfoResponseDtoList = storeService.findAllStores();
        return ResultResponse.of(ResultCode.GET_ALL_THE_STORES_SUCCESS, storeInfoResponseDtoList);
    }

    @GetMapping(value = "store/search")
    public ResultResponse searchStores(@RequestParam String query) {
        List<StoreInfoResponseDto> storeInfoResponseDtoList = storeService.findStore(query);
        return ResultResponse.of(ResultCode.SEARCH_STORES_SUCCESS, storeInfoResponseDtoList);
    }

    @DeleteMapping(value = "/store/delete/{storeId}")
    public ResultResponse deleteStore(@PathVariable("storeId") Long storeId, @RequestBody String password) {
        StoreDeleteResponseDto storeDeleteResponseDto = storeService.deleteStore(storeId, password);
        return ResultResponse.of(ResultCode.DELETE_SPECIFIC_STORE_SUCCESS, storeDeleteResponseDto);
    }
}
