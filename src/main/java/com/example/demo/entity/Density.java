package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Entity
@Getter
public class Density {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long densityId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private int densityRate;

    private int people_count;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime calculatedTime;
}
