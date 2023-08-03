package com.example.seatitssu.controller;

import com.example.seatitssu.dto.store.StoreDeleteRequestDto;
import com.example.seatitssu.dto.store.StoreDeleteResponseDto;
import com.example.seatitssu.dto.store.StoreFindNearByResponseDto;
import com.example.seatitssu.dto.store.StoreInfoRequestDto;
import com.example.seatitssu.dto.store.StoreInfoResponseDto;
import com.example.seatitssu.dto.store.StoreRegisterResponseDto;
import com.example.seatitssu.global.result.ResultCode;
import com.example.seatitssu.global.result.ResultResponse;
import com.example.seatitssu.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping(value = "/stores")
    public ResultResponse register(@Valid @RequestBody StoreInfoRequestDto storeInfoRequestDto) {
        Long registeredStoreId = storeService.register(storeInfoRequestDto.toEntity());
        return ResultResponse.of(ResultCode.REGISTER_STORE_SUCCESS, StoreRegisterResponseDto.of(registeredStoreId));
    }

    @PatchMapping(value = "/stores/{storeId}")
    public ResultResponse updateStore(@PathVariable("storeId") Long storeId, @RequestBody StoreInfoRequestDto storeInfoRequestDto) {
        Long registeredStoreId = storeService.updateStore(storeId, storeInfoRequestDto.toEntity());
        return ResultResponse.of(ResultCode.UPDATE_STORE_INFO_SUCCESS, StoreRegisterResponseDto.of(registeredStoreId));
    }

    @GetMapping(value = "/stores/{storeId}")
    public ResultResponse findStore(@PathVariable("storeId")Long storeId) {
        System.out.println("findStore 컨트롤러 메소드 실행");
        StoreInfoResponseDto storeInfoResponseDto = storeService.findStore(storeId);
        return ResultResponse.of(ResultCode.GET_SPECIFIC_STORE_SUCCESS, storeInfoResponseDto);
    }

    @GetMapping(value = "/stores/nearby")
    public ResultResponse findNearByStores(@RequestParam double radius, @RequestParam double latitude, @RequestParam double longitude) {
        List<StoreFindNearByResponseDto> storeFindNearByResponseDtoList = storeService.findNearByStores(radius, latitude, longitude);
        return ResultResponse.of(ResultCode.GET_NEARBY_STORES_SUCCESS, storeFindNearByResponseDtoList);
    }

    @GetMapping(value = "/stores")
    public ResultResponse findAllStores() {
        List<StoreInfoResponseDto> storeInfoResponseDtoList = storeService.findAllStores();
        return ResultResponse.of(ResultCode.GET_ALL_THE_STORES_SUCCESS, storeInfoResponseDtoList);
    }

    @GetMapping(value = "/stores/search")
    public ResultResponse searchStores(@RequestParam String query) {
        List<StoreInfoResponseDto> storeInfoResponseDtoList = storeService.findStore(query);
        return ResultResponse.of(ResultCode.SEARCH_STORES_SUCCESS, storeInfoResponseDtoList);
    }

    @PutMapping(value = "/stores/{storeId}")
    public ResultResponse deleteStore(@PathVariable("storeId") Long storeId, @RequestBody StoreDeleteRequestDto storeDeleteRequestDto) {
        StoreDeleteResponseDto storeDeleteResponseDto = storeService.deleteStore(storeId, storeDeleteRequestDto);
        return ResultResponse.of(ResultCode.DELETE_SPECIFIC_STORE_SUCCESS, storeDeleteResponseDto);
    }
}
