package com.example.demo.repository;

import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByStoreNameContaining(String query);

}
