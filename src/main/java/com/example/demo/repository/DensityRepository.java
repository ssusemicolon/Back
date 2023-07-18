package com.example.demo.repository;

import com.example.demo.entity.Density;
import com.example.demo.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DensityRepository extends JpaRepository<Density, Long> {
}
