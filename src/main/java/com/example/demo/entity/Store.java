package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name")
    private String storeName;

    private String address;

    private double latitude;

    private double longitude;
    @OneToOne
    @JoinColumn(name = "business_days_id")
    private BusinessDays businessDays;

    @OneToOne
    @JoinColumn(name = "business_hours_id")
    private BusinessHours businessHours;

    @OneToMany(mappedBy = "store")
    List<Density> densityList = new ArrayList<>();
}
