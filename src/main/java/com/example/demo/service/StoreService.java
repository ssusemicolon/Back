package com.example.demo.service;

import com.example.demo.dto.StoreInfoResponseDto;
import com.example.demo.entity.Store;
import com.example.demo.repository.BusinessDaysRepository;
import com.example.demo.repository.BusinessHoursRepository;
import com.example.demo.repository.DensityRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
