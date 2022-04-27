package com.example.hfb.service;

import com.example.hfb.model.ResponseData;
import org.springframework.http.ResponseEntity;

public interface StatisticService {
    ResponseEntity<ResponseData> statisticDonation (String startDate, String endDate);
    ResponseEntity<ResponseData> statisticFood (String startDate, String endDate);
    ResponseEntity<ResponseData> statisticRequest (String startDate, String endDate);
}
