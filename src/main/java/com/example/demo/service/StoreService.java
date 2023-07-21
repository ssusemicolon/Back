package com.example.demo.service;

import com.example.demo.dto.store.StoreDeleteResponseDto;
import com.example.demo.dto.store.StoreInfoResponseDto;
import com.example.demo.entity.BusinessDays;
import com.example.demo.entity.BusinessHours;
import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public Long register(Store store) {
        return storeRepository.save(store).getId();
    }

    public Long updateStore(Long storeId, Store updatedStore) {
        Store store = storeRepository.findById(storeId).orElse(null);
        BusinessDays businessDays = store.getBusinessDays();
        BusinessHours businessHours = store.getBusinessHours();

        store.setStoreName(updatedStore.getStoreName());
        store.setImageUrl(updatedStore.getImageUrl());
        store.setSeatCount(updatedStore.getSeatCount());
        store.setPassword(updatedStore.getPassword());
        store.setAddress(updatedStore.getAddress());
        store.setLatitude(updatedStore.getLatitude());
        store.setLongitude(updatedStore.getLongitude());

        businessDays.setSunday(updatedStore.getBusinessDays().getSunday());
        businessDays.setMonday(updatedStore.getBusinessDays().getMonday());
        businessDays.setTuesday(updatedStore.getBusinessDays().getTuesday());
        businessDays.setWednesday(updatedStore.getBusinessDays().getWednesday());
        businessDays.setThursday(updatedStore.getBusinessDays().getThursday());
        businessDays.setFriday(updatedStore.getBusinessDays().getFriday());
        businessDays.setSaturday(updatedStore.getBusinessDays().getSaturday());

        businessHours.setOpenBusinessHour(updatedStore.getBusinessHours().getOpenBusinessHour());
        businessHours.setCloseBusinessHour(updatedStore.getBusinessHours().getCloseBusinessHour());

        return storeRepository.save(store).getId();
    }

    public StoreInfoResponseDto findStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            System.out.println("==============찾으시는 매장이 없습니다.==============");
            return null;
        }
        return StoreInfoResponseDto.of(store);
    }

    public List<StoreInfoResponseDto> findStore(String query) {
        List<Store> storeList = storeRepository.findByStoreNameContaining(query);

        List<StoreInfoResponseDto> storeInfoResponseDtoList = new ArrayList<>();
        for (Store store : storeList) {
            storeInfoResponseDtoList.add(StoreInfoResponseDto.of(store));
        }

        return storeInfoResponseDtoList;
    }

    public List<StoreInfoResponseDto> findAllStores() {
        List<Store> storeList = storeRepository.findAll();

        List<StoreInfoResponseDto> storeInfoResponseDtoList = new ArrayList<>();
        for (Store store : storeList) {
            storeInfoResponseDtoList.add(StoreInfoResponseDto.of(store));
        }

        return storeInfoResponseDtoList;
    }

    public StoreDeleteResponseDto deleteStore(Long storeId, String password) {
        Store store = storeRepository.findById(storeId).orElse(null);

        if (store == null) {
            System.out.println("=============해당 매장이 존재하지 않습니다.==========");
        }
        storeRepository.delete(store);
        return StoreDeleteResponseDto.of(storeId);
    }
}
