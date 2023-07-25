package com.example.seatitssu.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BusinessHours {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void updateOpenBusinessHour(int openBusinessHour) {
        if (this.openBusinessHour != openBusinessHour) {
            this.openBusinessHour = openBusinessHour;
        }
    }

    public void updateCloseBusinessHour(int closeBusinessHour) {
        if (this.closeBusinessHour != closeBusinessHour) {
            this.closeBusinessHour = closeBusinessHour;
        }
    }

    public void compareAndUpdate(BusinessHours businessHours) {
        updateOpenBusinessHour(businessHours.getOpenBusinessHour());
        updateCloseBusinessHour(businessHours.getCloseBusinessHour());
    }
}
