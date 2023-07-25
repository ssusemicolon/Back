package com.example.seatitssu.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BusinessDays {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public BusinessDays(List<Day> dayList) {
        this.sunday = dayList.get(0);
        this.monday = dayList.get(1);
        this.tuesday = dayList.get(2);
        this.wednesday = dayList.get(3);
        this.thursday = dayList.get(4);
        this.friday = dayList.get(5);
        this.saturday = dayList.get(6);
    }

    public List<Day> getBusinessDayList() {
        List<Day> businessDayList = new ArrayList<>();
        businessDayList.add(sunday);
        businessDayList.add(monday);
        businessDayList.add(tuesday);
        businessDayList.add(wednesday);
        businessDayList.add(thursday);
        businessDayList.add(friday);
        businessDayList.add(saturday);

        return businessDayList;
    }

    public void updateSunday(Day sunday) {
        if (this.sunday == null || !this.sunday.equals(sunday)) {
            this.sunday = sunday;
        }
    }

    public void updateMonday(Day monday) {
        if (this.monday == null || !this.monday.equals(monday)) {
            this.monday = monday;
        }
    }
    public void updateTuesday(Day tuesday) {
        if (this.tuesday == null || !this.tuesday.equals(tuesday)) {
            this.tuesday = tuesday;
        }
    }

    public void updateWednesday(Day wednesday) {
        if (this.wednesday == null || !this.wednesday.equals(wednesday)) {
            this.wednesday = wednesday;
        }
    }

    public void updateThursday(Day thursday) {
        if (this.thursday == null || !this.thursday.equals(thursday)) {
            this.thursday = thursday;
        }
    }

    public void updateFriday(Day friday) {
        if (this.friday == null || !this.friday.equals(friday)) {
            this.friday = friday;
        }
    }
    public void updateSaturday(Day saturday) {
        if (this.saturday == null || !this.saturday.equals(saturday)) {
            this.saturday = saturday;
        }
    }


    public void compareAndUpdate(BusinessDays businessDays) {
        updateSunday(businessDays.getSunday());
        updateMonday(businessDays.getMonday());
        updateTuesday(businessDays.getTuesday());
        updateWednesday(businessDays.getWednesday());
        updateThursday(businessDays.getThursday());
        updateFriday(businessDays.getFriday());
        updateSaturday(businessDays.getSaturday());
    }
}
