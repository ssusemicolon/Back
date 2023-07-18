package com.example.demo.repository;

import com.example.demo.entity.BusinessDays;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessDaysRepository extends JpaRepository<BusinessDays, Long> {
}
