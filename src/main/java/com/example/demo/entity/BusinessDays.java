package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class BusinessDays {
    @Id @GeneratedValue
    @Column(name = "business_days_id")
    private Long id;

    @OneToOne(mappedBy = "businessDays")
    private Store store;

    @Enumerated(EnumType.STRING)
    private Day sunday;
    @Enumerated(EnumType.STRING)
    private Day monday;
    @Enumerated(EnumType.STRING)
    private Day tuesday;
    @Enumerated(EnumType.STRING)
    private Day wednesday;
    @Enumerated(EnumType.STRING)
    private Day thursday;
    @Enumerated(EnumType.STRING)
    private Day friday;
    @Enumerated(EnumType.STRING)
    private Day saturday;

    @Builder
    public BusinessDays(Day sunday, Day monday, Day tuesday, Day wednesday, Day thursday, Day friday, Day saturday) {
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }
}
