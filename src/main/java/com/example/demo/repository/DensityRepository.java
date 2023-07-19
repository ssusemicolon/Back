package com.example.demo.repository;

import com.example.demo.entity.Density;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DensityRepository extends JpaRepository<Density, Long> {

    // 질문: 연관관계 주인인 Density 엔티티가 멤버 객체로 Store를 들고 있는데, storeId를 입력받아서 JPQL을 실행할 수는 없는 것인가?
    // 무조건 파라미터로 Store 객체를 갖고 와야 하는가?
    @Query(value = "SELECT d FROM Density AS d where d.store = :store")
    List<Density> findByStoreId(@Param("store") Store store);

    // 내가 하고싶은 거
    List<Density> findByStoreId(@Param("storeId") Long storeId);
}
