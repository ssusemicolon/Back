package com.example.seatitssu.service;

import com.example.seatitssu.dto.store.StoreInfoResponseDto;
import com.example.seatitssu.entity.BusinessDays;
import com.example.seatitssu.entity.BusinessHours;
import com.example.seatitssu.entity.Day;
import com.example.seatitssu.entity.Store;
import com.example.seatitssu.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("매장의 정보를 변경해본다")
    void 매장_이름_변경() throws Exception {
        // given
        Day[] businessDaysList = { Day.OPEN, Day.OPEN, Day.OPEN, Day.OPEN, Day.OPEN, Day.OPEN, Day.CLOSED };
        Store store = new Store("베어메이드", null, 40, "bear", null, 0.0, 0.0, null, null);
        store.setBusinessHours(new BusinessHours(9, 22));
        store.setBusinessDays(new BusinessDays(Arrays.asList(businessDaysList)));
        Long storeId = storeService.register(store);

        // when
        store.updateStoreName("폭스메이드");
        System.out.println("==========================");
        Long updatedStoreId = storeService.updateStore(storeId, store);
        System.out.println("==========================");
        Store updatedStore = storeRepository.findById(updatedStoreId).orElse(null);

        // then
        assertThat(updatedStoreId).isEqualTo(storeId);
        assertThat(updatedStore.getStoreName()).isEqualTo("폭스메이드");
    }

}