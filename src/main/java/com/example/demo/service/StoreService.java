package com.example.demo.service;

import com.example.demo.entity.BusinessDays;
import com.example.demo.entity.BusinessHours;
import com.example.demo.entity.Density;
import com.example.demo.entity.Store;
import com.example.demo.repository.BusinessDaysRepository;
import com.example.demo.repository.BusinessHoursRepository;
import com.example.demo.repository.DensityRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final BusinessDaysRepository businessDaysRepository;
    private final BusinessHoursRepository businessHoursRepository;
    private final DensityRepository densityRepository;
    public Long register(String storeName, int seatCount, String password, String address, double latitude, double longitude,
                               BusinessDays businessDays, BusinessHours businessHours, Density... densityArr) {
        // Store를 가져올 때, 이미 Store 안에 BusinessDays, BusinessHours 객체가
        // 들어가 있어야 해. 이걸 어케 하지? Controller에서?
        Store store = Store.createStore(storeName, seatCount, password, address, latitude, longitude, businessDays, businessHours, densityArr);
        businessDaysRepository.save(businessDays);
        businessHoursRepository.save(businessHours);
        densityRepository.saveAll(Arrays.asList(densityArr));
        return storeRepository.save(store).getId();
    }
}
