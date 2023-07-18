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
    private Long id;

    @Column(name = "store_name")
    private String storeName;
    private int seatCount;

    private String password;
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

    public static Store createStore(String storeName, int seatCount, String password, String address, double latitude, double longitude,
                                    BusinessDays businessDays, BusinessHours businessHours, Density... densityArr) {
        Store store = new Store();
        store.setStoreName(storeName);
        store.setSeatCount(seatCount);
        store.setPassword(password);
        store.setAddress(address);
        store.setLatitude(latitude);
        store.setLongitude(longitude);
        store.setBusinessDays(businessDays);
        store.setBusinessHours(businessHours);

        for (Density density : densityArr) {
            store.addDensity(density);
        }

        return store;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    private void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    private void setPassword(String password) {
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
