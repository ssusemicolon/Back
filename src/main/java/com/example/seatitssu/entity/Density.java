package com.example.seatitssu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "density_id")
    private Long id;

    @NotNull
    @Column(name = "density_rate")
    private int densityRate;

    @NotNull
    @Column(name = "calculated_time")
    private LocalDateTime calculatedTime;

    @ManyToOne @NotNull
    @JoinColumn(name = "store_id")
    private Store store;

    public boolean IsCalculatedAtThisHour(int hour) {
        return calculatedTime.getHour() == hour;
    }

    public boolean IsCalculatedAtThisDay(int day) {
        return calculatedTime.getDayOfMonth() == day;
    }
}
