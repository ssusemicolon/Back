package com.example.seatitssu.service;

import com.example.seatitssu.dto.density.DensityGetRecentResponseDto;
import com.example.seatitssu.dto.density.DensityGetSpecificDayResponseDto;
import com.example.seatitssu.dto.density.DensityGetWeekResponseDto;
import com.example.seatitssu.dto.density.DensityUpdateRequestDto;
import com.example.seatitssu.entity.Density;
import com.example.seatitssu.entity.Store;
import com.example.seatitssu.global.error.ErrorCode;
import com.example.seatitssu.global.error.exception.EntityNotFoundException;
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
        if (store == null) {
            throw new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND);
        }

        int densityRate = (int)(((double)densityUpdateRequestDto.getPersonCount() / store.getSeatCount()) * 100);
        Density density = new Density(store, densityRate, densityUpdateRequestDto.getWhen());
        densityRepository.save(density);
    }

    public DensityGetRecentResponseDto getRecentDensity(Long storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            throw new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND);
        }

        List<Density> densityList = store.getDensityList();
        if (densityList.size() == 0) {
            throw new EntityNotFoundException(ErrorCode.DENSITY_NOT_FOUND);
        }

        Density recentDensity = densityList.get(densityList.size() - 1);
        return DensityGetRecentResponseDto.of(storeId, recentDensity);
        // 또 다른 방법: Density 테이블에서 입력받은 storeId랑 동일한 거 싹 다 찾은 다음, 시간 순 중 가장 빠른 거 뱉기.
    }

    public DensityGetSpecificDayResponseDto getSpecificDayDensity(Long storeId, LocalDate specificDate) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            throw new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND);
        }

        int openBusinessHour = store.getBusinessHours().getOpenBusinessHour();
        int closeBusinessHour = store.getBusinessHours().getCloseBusinessHour();

        List<Density> densityList = densityRepository.findSpecificDayDensityList(storeId, specificDate.toString());

        // 영업 시작 시간부터 영업 종료 시간까지의 시간별 밀집도 평균을 리스트 'densityPerHourList"에 저장함.
        // ex) 09시 ~ 13시 영업인 경우: [30, 40, 50, 40] 총 4개로, 9시, 10시, 11시, 12시의 평균 밀집도를 보여줌.
        // Q: 왜 13시 평균은 없나요?
        // A: 13시에 문을 닫기 때문에 평균을 낼 수 없음. 평균은 x시 0분 ~ x시 59분 사이의 밀집도 평균.
        // 만약, 1시부터 2시까지 밀집도가 아예 기록되어 있지 않은 경우, 1시의 평균 밀집도는 0으로 계산한다.
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
        if (store == null) {
            throw new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND);
        }

        List<Integer> densityPerDayList = new ArrayList<>();
        // today => 2023-07-28
        // 2023-07-28 - 7 = 2023-07-21
        // 요청 날짜로부터 일주일 전인 날짜부터 시작
        // 만약, 특정 날짜의 밀집도가 아예 기록되어 있지 않은 경우, 해당 날짜의 평균 밀집도는 0으로 표시한다.
        // 휴일인 날 => 밀집도 값 0
        // 영업일이어도 밀집도가 기록되지 않은 날 => 밀집도 값 0
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
