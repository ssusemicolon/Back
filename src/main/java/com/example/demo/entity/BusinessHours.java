package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
public class BusinessHours {
    @Id @GeneratedValue
    @Column(name = "business_hours_id")
    private Long id;

    @OneToOne(mappedBy = "businessHours")
    private Store store;

    private int openBusinessHour;

    private int closeBusinessHour;

    @Builder
    public BusinessHours(int openBusinessHour, int closeBusinessHour) {
        this.openBusinessHour = openBusinessHour;
        this.closeBusinessHour = closeBusinessHour;
    }
}
