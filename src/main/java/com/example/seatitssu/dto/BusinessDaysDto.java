package com.example.seatitssu.dto;

import com.example.seatitssu.entity.BusinessDays;
import com.example.seatitssu.entity.Day;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaysDto {
    public BusinessDaysDto(List<Integer> businessDays) {
        List<Day> convertedBusinessDays = new ArrayList<>();
        for (Integer businessDay : businessDays) {
            if (businessDay == 1) {
                convertedBusinessDays.add(Day.OPEN);
            } else {
                convertedBusinessDays.add(Day.CLOSED);
            }
        }

        this.sunday = convertedBusinessDays.get(0);
        this.monday = convertedBusinessDays.get(1);
        this.tuesday = convertedBusinessDays.get(2);
        this.wednesday = convertedBusinessDays.get(3);
        this.thursday = convertedBusinessDays.get(4);
        this.friday = convertedBusinessDays.get(5);
        this.saturday = convertedBusinessDays.get(6);
    }
    private Long id;
    private Day sunday;
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;

    public BusinessDays toEntity() {
        return BusinessDays.builder()
                .sunday(this.sunday)
                .monday(this.monday)
                .tuesday(this.tuesday)
                .wednesday(this.wednesday)
                .thursday(this.thursday)
                .friday(this.friday)
                .saturday(this.saturday)
                .build();
    }
}
