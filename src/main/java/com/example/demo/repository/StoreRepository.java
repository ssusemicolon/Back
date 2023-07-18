package com.example.demo.repository;

import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface StoreRepository extends JpaRepository<Store, Long> {
//    @PersistenceContext
//    private EntityManager em;

    //    public Long reister(Store store) {
//        em.persist(store);
//        return store.getId();
//    }
//
//    public Store find(Long id) {
//        return em.find(Store.class, id);
//    }
}
