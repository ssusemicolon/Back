package com.example.demo.repository;

import com.example.demo.entity.BusinessHours;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
}
