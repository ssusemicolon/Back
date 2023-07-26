package com.example.seatitssu.service;

import com.example.seatitssu.dto.NearByStoreInterface;
import com.example.seatitssu.dto.store.StoreDeleteResponseDto;
import com.example.seatitssu.dto.store.StoreFindNearByRequestDto;
import com.example.seatitssu.dto.store.StoreFindNearByResponseDto;
import com.example.seatitssu.dto.store.StoreInfoResponseDto;
import com.example.seatitssu.entity.BusinessDays;
import com.example.seatitssu.entity.BusinessHours;
import com.example.seatitssu.entity.Density;
import com.example.seatitssu.entity.Store;
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
        if (store == null ) {
            System.out.println("============변경할 매장이 존재하지 않습니다.===============");
        }

        store.compareAndUpdate(store);

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

    public List<StoreInfoResponseDto> findStore(String query) {
        List<Store> storeList = storeRepository.findByStoreNameContaining(query);

        List<StoreInfoResponseDto> storeInfoResponseDtoList = new ArrayList<>();
        for (Store store : storeList) {
            storeInfoResponseDtoList.add(StoreInfoResponseDto.of(store));
        }

        return storeInfoResponseDtoList;
    }

    public List<StoreFindNearByResponseDto> findNearByStores(StoreFindNearByRequestDto storeFindNearByRequestDto) {
        double radius = storeFindNearByRequestDto.getRadius();
        double latitude = storeFindNearByRequestDto.getLatitude();
        double longitude = storeFindNearByRequestDto.getLongitude();
        List<NearByStoreInterface> nearByStoreInterfaceList = storeRepository.findNearByStores(radius, latitude, longitude);

        List<StoreFindNearByResponseDto> storeFindNearByResponseDtoList = new ArrayList<>();
        for (NearByStoreInterface nearByStoreinterface : nearByStoreInterfaceList) {
            Long storeId = nearByStoreinterface.getStoreId();
            Store store = storeRepository.findById(storeId).orElse(null);

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

    public StoreDeleteResponseDto deleteStore(Long storeId, String password) {
        Store store = storeRepository.findById(storeId).orElse(null);

        if (store == null) {
            System.out.println("=============해당 매장이 존재하지 않습니다.==========");
        }
        storeRepository.delete(store);
        return StoreDeleteResponseDto.of(storeId);
    }
}
