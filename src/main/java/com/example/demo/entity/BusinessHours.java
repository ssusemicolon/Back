package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessHours {
    @Id
    private Long storeId;

    private int openBusinessHour;

    private int closeBusinessHour;
}
