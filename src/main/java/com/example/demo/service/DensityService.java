package com.example.demo.service;

import com.example.demo.dto.density.DensityGetRecentResponseDto;
import com.example.demo.dto.density.DensityGetSpecificDayResponseDto;
import com.example.demo.dto.density.DensityUpdateRequestDto;
import com.example.demo.entity.Density;
import com.example.demo.entity.Store;
import com.example.demo.repository.DensityRepository;
import com.example.demo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DensityService {
    private final StoreRepository storeRepository;
    private final DensityRepository densityRepository;

    public void updateDensity(DensityUpdateRequestDto densityUpdateRequestDto) {
        Long storeId = densityUpdateRequestDto.getStoreId();
        Store store = storeRepository.findById(storeId).orElse(null);
        Density density = new Density(store, densityUpdateRequestDto.getDensity(), densityUpdateRequestDto.getWhen());
        densityRepository.save(density);
    }

    public DensityGetRecentResponseDto getRecentDensity(Long storeId) {
        // 첫 번째 방법: 입력받은 id를 가진 Store 객체에 접근해서 densityList 중 가장 첫 번째 요소를 가져오기
        // 전제 조건: 계산된 순서대로 밀집도의 갱신 요청이 온다. 그래야, 리스트에 시간 순서대로 쌓임.
        Store store = storeRepository.findById(storeId).orElse(null);
        List<Density> densityList = store.getDensityList();
        if (densityList == null) {
            System.out.println("=================densityList가 null이에요!!!!==================");
            return null;
        }
        return DensityGetRecentResponseDto.of(densityList.get(0));


        // 첫 번째 방법 - 2
        /*
         Density density = store.getDensityList().get(0);
         return DensityGetRecentResponseDto.of(density);
        */

        // 두 번째 방법: Density 테이블에서 입력받은 storeId랑 동일한 거 싹 다 찾은 다음, 시간 순 중 가장 빠른 거 뱉기.
    }

    public DensityGetSpecificDayResponseDto getSpecificDayDensity(Long storeId, LocalDate specificDate) {
        Store store = storeRepository.findById(storeId).orElse(null);
        int openBusinessHour = store.getBusinessHours().getOpenBusinessHour();
        int closeBusinessHour = store.getBusinessHours().getCloseBusinessHour();

        List<Density> densityList = densityRepository.findSpecificDayDensityList(storeId, specificDate.toString());

        List<Integer> densityPerHourList = new ArrayList<>();
        for (int i = openBusinessHour; i < closeBusinessHour; i++) {
            int densityRateSum = 0;
            int densityCount = 0;
            Iterator<Density> iter = densityList.iterator();
            while (iter.hasNext()) {
                Density density = iter.next();
                if (density.IsCalculatedAtThisHour(i) == true) {
                    densityRateSum += density.getDensityRate();
                    densityCount++;
                    iter.remove();
                }
            }

            int densityPerHour = 0;
            if (densityCount != 0) {
                densityPerHour = densityRateSum / densityCount;
            }
            densityPerHourList.add(densityPerHour);
        }

        return DensityGetSpecificDayResponseDto.of(storeId, openBusinessHour, closeBusinessHour, densityPerHourList);
    }
}
