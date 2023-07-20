package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
@NoArgsConstructor
public class Store {
    public Store(String storeName, String imageUrl, int seatCount, String password,
                 String address, double latitude, double longitude,
                 BusinessDays businessDays, BusinessHours businessHours) {
        this.storeName = storeName;
        this.imageUrl = imageUrl;
        this.seatCount = seatCount;
        this.password = password;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.businessDays = businessDays;
        this.businessHours = businessHours;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "seat_count")
    private int seatCount;

    private String password;
    private String address;
    private double latitude;
    private double longitude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_days_id")
    private BusinessDays businessDays;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_hours_id")
    private BusinessHours businessHours;

    @OneToMany(mappedBy = "store")
    List<Density> densityList = new ArrayList<>();

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setBusinessDays(BusinessDays businessDays) {
        this.businessDays = businessDays;
    }

    public void setBusinessHours(BusinessHours businessHours) {
        this.businessHours = businessHours;
    }

    public void addDensity(Density density) {
        densityList.add(density);
        density.setStore(this);
    }
}
