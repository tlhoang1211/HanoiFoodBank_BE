package com.example.hfb.model.dto;

import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StatisticFood {
    private String dateTime;
    private Long food;

    public StatisticFood(Long dateTime, Long donate) {
        this.dateTime = Utilities.convertLongToDate(dateTime);;
        this.food = donate;
    }
}
