package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
public class BusinessDays {
    @Id
    private Long storeId;

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
}
