package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Density {
    @Builder
    public Density(Store store, int densityRate, LocalDateTime calculatedTime) {
        this.store = store;
        this.densityRate = densityRate;
        this.calculatedTime = calculatedTime;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "density_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "density_rate")
    private int densityRate;

    @Column(name = "calculated_time")
    private LocalDateTime calculatedTime;


    public boolean IsCalculatedAtThisHour(int hour) {
        return calculatedTime.getHour() == hour;
    }

    public boolean IsCalculatedAtThisDay(int day) {
        return calculatedTime.getDayOfMonth() == day;
    }
}
