package com.example.seatitssu.service;

import com.example.seatitssu.dto.density.DensityGetRecentResponseDto;
import com.example.seatitssu.dto.density.DensityGetSpecificDayResponseDto;
import com.example.seatitssu.dto.density.DensityGetWeekResponseDto;
import com.example.seatitssu.dto.density.DensityUpdateRequestDto;
import com.example.seatitssu.entity.Density;
import com.example.seatitssu.entity.Store;
import com.example.seatitssu.repository.DensityRepository;
import com.example.seatitssu.repository.StoreRepository;
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
        int densityRate = (int)(((double)densityUpdateRequestDto.getPersonCount() / store.getSeatCount()) * 100);
        Density density = new Density(store, densityRate, densityUpdateRequestDto.getWhen());
        densityRepository.save(density);
    }

    public DensityGetRecentResponseDto getRecentDensity(Long storeId) {
        // 첫 번째 방법: 입력받은 id를 가진 Store 객체에 접근해서 densityList 중 가장 마지막 요소를 가져오기
        // 전제 조건: 계산된 순서대로 밀집도의 갱신 요청이 온다. 그래야, 가장 최근에 계산된 밀집도가 리스트 마지막에 있게 된다.
        Store store = storeRepository.findById(storeId).orElse(null);
        List<Density> densityList = store.getDensityList();
        if (densityList == null) {
            System.out.println("=================densityList가 null이에요!!!!==================");
            return null;
        }
        Density recentDensity = densityList.get(densityList.size() - 1);
        return DensityGetRecentResponseDto.of(recentDensity);

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

    public DensityGetWeekResponseDto getWeekDensity(Long storeId, LocalDate today) {
        Store store = storeRepository.findById(storeId).orElse(null);
        List<Integer> densityPerDayList = new ArrayList<>();
        // today => 2023-07-28
        // 2023-07-28 - 7 =
        // 요청 날짜로부터 일주일 전 날짜에서 시작
        LocalDate day = today.minusDays(7);
        while (day.isBefore(today)) {
            List<Density> densityList = densityRepository.findSpecificDayDensityList(storeId, day.toString());
            int densityRateSum = 0;
            int densityCount = densityList.size();
            for (Density density : densityList) {
                densityRateSum += density.getDensityRate();
            }

            int densityPerDay = 0;
            if (densityCount != 0) {
                densityPerDay = densityRateSum / densityCount;
            }

            densityPerDayList.add(densityPerDay);
            day = day.plusDays(1);
        }

        return DensityGetWeekResponseDto.of(storeId, store.getBusinessDays().getBusinessDayList(), densityPerDayList);
    }
}
