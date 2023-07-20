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


    /**
     * 현재 시간으로부터 24시간 동안의 밀집도 리스트를 조회하는 메소드
     */
    @Query(value = "SELECT * FROM density WHERE (store_id = :storeId) AND calculated_time BETWEEN DATE_ADD(NOW(),INTERVAL -1 DAY) AND NOW()", nativeQuery = true)
    List<Density> findRecentDayDensityList(@Param("storeId") Long storeId);

    /**
     *
     * @param storeId: Long 타입의 store 객체 id
     * @param specificDate: "YYYY-mm-dd" 형태의 String 타입 날짜 문자열
     * @return 입력받은 id를 가진 매장의 입력받은 날짜에 계산된 밀집도인 Density 리스트
     */
    @Query(value = "SELECT * FROM density WHERE (store_id = :storeId) AND date_format(calculated_time, \"%Y-%m-%d\") = :specificDate", nativeQuery = true)
    List<Density> findSpecificDayDensityList(@Param("storeId") Long storeId, @Param("specificDate") String specificDate);
}
