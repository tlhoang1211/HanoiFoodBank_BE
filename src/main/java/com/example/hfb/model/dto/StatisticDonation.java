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
public class StatisticDonation {
    private String dateTime;
    private Double donate;

    public StatisticDonation(Long dateTime, Double donate) {
        this.dateTime = Utilities.convertLongToDate(dateTime);;
        this.donate = donate;
    }
}
