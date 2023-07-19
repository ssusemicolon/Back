package com.example.demo.service;

import com.example.demo.dto.store.StoreInfoResponseDto;
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

    public StoreInfoResponseDto findStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            System.out.println("==============찾으시는 매장이 없습니다.==============");
            return null;
        }
        return StoreInfoResponseDto.of(store);
    }

    public List<StoreInfoResponseDto> findAllStores() {
        List<Store> storeList = storeRepository.findAll();

        List<StoreInfoResponseDto> storeInfoResponseDtoList = new ArrayList<>();
        for (Store store : storeList) {
            storeInfoResponseDtoList.add(StoreInfoResponseDto.of(store));
        }

        return storeInfoResponseDtoList;
    }
}
