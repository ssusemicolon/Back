package com.example.demo.service;

import com.example.demo.dto.DensityDto;
import com.example.demo.entity.Density;
import com.example.demo.entity.Store;
import com.example.demo.repository.DensityRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DensityService {
    private final StoreRepository storeRepository;
    private final DensityRepository densityRepository;

    public void updateDensity(DensityDto densityDto) {
        System.out.println("주어진 storeId: " + densityDto.getStoreId());
        Store store = storeRepository.findById(densityDto.getStoreId()).orElse(null);
        Density density = new Density(store, densityDto.getDensity(), densityDto.getWhen());
        densityRepository.save(density);
    }
}
