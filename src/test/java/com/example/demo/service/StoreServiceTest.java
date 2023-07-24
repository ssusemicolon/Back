package com.example.demo.service;

import com.example.demo.dto.store.StoreInfoResponseDto;
import com.example.demo.entity.BusinessDays;
import com.example.demo.entity.BusinessHours;
import com.example.demo.entity.Day;
import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.assertj.core.api.Assertions;

import javax.transaction.Transactional;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;


    @Test
    public void 매장_등록() throws Exception {
        //given
        Store store = new Store();
        store.setStoreName("손커피연구소");

        //when
        Long storeId = storeService.register(store);

        //then
        assertThat(store).isEqualTo(storeRepository.findById(storeId).orElse(null));

    }

    @Test
    @DisplayName("매장의 고유번호인 storeId를 이용하여 특정 매장을 조회한다")
    void 고유번호를_이용한_매장_조회() throws Exception {
        //given
        Day[] businessDaysList = { Day.OPEN, Day.OPEN, Day.OPEN, Day.OPEN, Day.OPEN, Day.OPEN, Day.CLOSED };
        Store store = new Store("베어메이드", null, 40, "bear", null, 0.0, 0.0, null, null);
        store.setBusinessHours(new BusinessHours(9, 22));
        store.setBusinessDays(new BusinessDays(Arrays.asList(businessDaysList)));
        Long storeId = storeService.register(store);

        //when
        StoreInfoResponseDto storeInfoResponseDto = storeService.findStore(storeId);
        Long findStoreId = storeInfoResponseDto.getStoreId();

        //then
        // storeId는 primary key로 해당 값이 같을 경우, 같은 컬럼임을 의미한다.
        assertThat(findStoreId).isEqualTo(storeId);

    }

}