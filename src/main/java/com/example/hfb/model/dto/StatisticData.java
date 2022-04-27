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
public class StatisticData {
    private String dateTime;
    private Object total;
}
