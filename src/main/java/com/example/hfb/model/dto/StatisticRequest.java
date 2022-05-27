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
public class StatisticRequest {
    private String dateTime;
    private Long total;

    public StatisticRequest(Long dateTime, Long donate) {
        this.dateTime = Utilities.convertLongToDate(dateTime);;
        this.total = donate;
    }
}
