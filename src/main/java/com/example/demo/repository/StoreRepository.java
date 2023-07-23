package com.example.demo.repository;

import com.example.demo.dto.NearByStoreInterface;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByStoreNameContaining(String query);

    @Query(value = "SELECT store_id as storeId, \n" +
            "    (\n" +
            "      6371 * acos (\n" +
            "      cos ( radians(:latitude) )\n" +
            "      * cos( radians(latitude) )\n" +
            "      * cos( radians(longitude) - radians(:longitude) )\n" +
            "      + sin ( radians(:latitude) )\n" +
            "      * sin( radians(latitude) )\n" +
            "    )\n" +
            ") AS distance\n" +
            "FROM store\n" +
            "HAVING distance < :radius \n" +
            "ORDER BY distance ASC;", nativeQuery = true)
    List<NearByStoreInterface> findNearByStores(double radius, double latitude, double longitude);
}
