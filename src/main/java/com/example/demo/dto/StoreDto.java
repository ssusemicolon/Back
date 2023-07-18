package com.example.demo.dto;

import com.example.demo.entity.BusinessDays;
import com.example.demo.entity.BusinessHours;
import com.example.demo.entity.Density;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

public class StoreDto {
    private String storeName;
    private int seatCount;
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
