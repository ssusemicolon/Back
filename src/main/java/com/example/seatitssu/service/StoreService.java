package com.example.seatitssu.service;

import com.example.seatitssu.dto.NearByStoreInterface;
import com.example.seatitssu.dto.store.StoreDeleteRequestDto;
import com.example.seatitssu.dto.store.StoreDeleteResponseDto;
import com.example.seatitssu.dto.store.StoreFindNearByResponseDto;
import com.example.seatitssu.dto.store.StoreInfoResponseDto;
import com.example.seatitssu.entity.Store;
import com.example.seatitssu.global.error.ErrorCode;
import com.example.seatitssu.global.error.exception.EntityNotFoundException;
import com.example.seatitssu.global.error.exception.PasswordFalseException;
import com.example.seatitssu.repository.StoreRepository;
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
        if (store == null) {
            throw new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND);
        }

        store.compareAndUpdate(updatedStore);
        return storeRepository.save(store).getId();
    }

    public StoreInfoResponseDto findStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            throw new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND);
        }

        return StoreInfoResponseDto.of(store);
    }

    public List<StoreInfoResponseDto> findStore(String query) {
        List<Store> storeList = storeRepository.findByStoreNameContaining(query);

        List<StoreInfoResponseDto> storeInfoResponseDtoList = new ArrayList<>();
        for (Store store : storeList) {
            // 질문: 이미, findByStoreNameContaining() 메소드를 통해, 실제 storeId가 있는 매장만 가져왔는데, 이게 없을 확률이 0인데도 null인 경우를 검사해야 하는가?
            storeInfoResponseDtoList.add(StoreInfoResponseDto.of(store));
        }

        return storeInfoResponseDtoList;
    }

    public List<StoreFindNearByResponseDto> findNearByStores(double radius, double latitude, double longitude) {
        System.out.println("radius = " + radius);
        System.out.println("latitude = " + latitude);
        System.out.println("longitude = " + longitude);
        List<NearByStoreInterface> nearByStoreInterfaceList = storeRepository.findNearByStores(radius, latitude, longitude);

        List<StoreFindNearByResponseDto> storeFindNearByResponseDtoList = new ArrayList<>();
        for (NearByStoreInterface nearByStoreinterface : nearByStoreInterfaceList) {
            // 질문: 이미, findNearByStore() 메소드를 통해, 실제 storeId가 있는 매장만 가져왔는데, 이게 없을 확률이 0인데도 null인 경우를 검사해야 하는가?
            Long storeId = nearByStoreinterface.getStoreId();
            Store store = storeRepository.findById(storeId).orElse(null);
            System.out.println("distance: " + nearByStoreinterface.getDistance());
            storeFindNearByResponseDtoList.add(StoreFindNearByResponseDto.of(store, nearByStoreinterface.getDistance()));
        }

        return storeFindNearByResponseDtoList;
    }

    public List<StoreInfoResponseDto> findAllStores() {
        List<Store> storeList = storeRepository.findAll();

        List<StoreInfoResponseDto> storeInfoResponseDtoList = new ArrayList<>();
        for (Store store : storeList) {
            storeInfoResponseDtoList.add(StoreInfoResponseDto.of(store));
        }

        return storeInfoResponseDtoList;
    }

    public StoreDeleteResponseDto deleteStore(Long storeId, StoreDeleteRequestDto storeDeleteRequestDto) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store == null) {
            throw new EntityNotFoundException(ErrorCode.STORE_NOT_FOUND);
        }

        String clientInputPassword = storeDeleteRequestDto.getPassword();
        if (clientInputPassword.equals(store.getPassword())) {
            storeRepository.delete(store);
        } else {
            // 비밀번호가 틀리면 200 결과? 아니면 401 결과?
            throw new PasswordFalseException(ErrorCode.STORE_FALSE_PASSWORD);
        }

        return StoreDeleteResponseDto.of(storeId);
    }
}
