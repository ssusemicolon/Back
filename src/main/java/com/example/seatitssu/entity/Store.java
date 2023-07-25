package com.example.seatitssu.entity;

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
    public Store(String storeName, String thumUrl, int seatCount, String password,
                 String address, double latitude, double longitude,
                 BusinessDays businessDays, BusinessHours businessHours) {
        this.storeName = storeName;
        this.thumUrl = thumUrl;
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

    @Column(name = "thum_url")
    private String thumUrl;

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

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    List<Density> densityList = new ArrayList<>();

    public void updateStoreName(String storeName) {
        if (this.storeName == null || !this.storeName.equals(storeName)) {
            this.storeName = storeName;
        }
    }

    public void updateThumUrl(String thumUrl) {
        if (this.thumUrl == null || !this.thumUrl.equals(thumUrl)) {
            this.thumUrl = thumUrl;
        }
    }

    public void updateSeatCount(int seatCount) {
        if (this.seatCount != seatCount) {
            this.seatCount = seatCount;
        }
    }

    public void updatePassword(String password) {
        if (this.password == null || !this.password.equals(password)) {
            this.password = password;
        }
    }

    public void updateAddress(String address) {
        if (this.address == null || !this.address.equals(address)) {
            this.address = address;
        }
    }

    public void updateLatitude(double latitude) {
        double epsilon = 0.000001d;

        if (Math.abs(this.latitude - latitude) > epsilon) {
            this.latitude = latitude;
        }
    }

    public void updateLongitude(double longitude) {
        double epsilon = 0.000001d;

        if (Math.abs(this.longitude - longitude) > epsilon) {
            this.longitude = longitude;
        }
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

    public void compareAndUpdate(Store store) {
        this.updateStoreName(store.getStoreName());
        this.updateThumUrl(store.getThumUrl());
        this.updateSeatCount(store.getSeatCount());
        this.updatePassword(store.getPassword());
        this.updateAddress(store.getAddress());
        this.updateLatitude(store.getLatitude());
        this.updateLongitude(store.getLongitude());
        this.businessDays.compareAndUpdate(store.getBusinessDays());
        this.businessHours.compareAndUpdate(store.getBusinessHours());
    }
}
