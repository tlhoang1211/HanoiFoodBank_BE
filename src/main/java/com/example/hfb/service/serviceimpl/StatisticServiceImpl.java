package com.example.hfb.service.serviceimpl;

import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.StatisticData;
import com.example.hfb.model.dto.StatisticDonation;
import com.example.hfb.model.dto.StatisticFood;
import com.example.hfb.model.dto.StatisticRequest;
import com.example.hfb.repository.DonationRepository;
import com.example.hfb.repository.FoodRepository;
import com.example.hfb.repository.RequestRepository;
import com.example.hfb.service.StatisticService;
import com.example.hfb.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public ResponseEntity<ResponseData> statisticDonation(String startDate, String endDate) {
        Long startDateL = Utilities.convertStringToLong(startDate);
        Long endDateL = Utilities.convertStringToLong(endDate);
        List<Object[]> statisticDonations = donationRepository.statisticDonation(startDateL, endDateL);
        List<StatisticData> data = Utilities.getListBetweenDate(startDate, endDate);
        for (Object[] item : statisticDonations) {
            for (StatisticData d : data) {
                if (d.getDateTime().equals(item[0])) {
                    d.setTotal( item[1]);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", data));
    }

    @Override
    public ResponseEntity<ResponseData> statisticFood(String startDate, String endDate) {
        Long startDateL = Utilities.convertStringToLong(startDate);
        Long endDateL = Utilities.convertStringToLong(endDate);
        List<Object[]> statisticFoods = foodRepository.statisticFood(startDateL, endDateL);
        List<StatisticData> data = Utilities.getListBetweenDate(startDate, endDate);
        for (Object[] item : statisticFoods) {
           for (StatisticData d : data) {
               if (d.getDateTime().equals(item[0])) {
                    d.setTotal( item[1]);
               }
           }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", data));
    }

    @Override
    public ResponseEntity<ResponseData> statisticRequest(String startDate, String endDate) {
        Long startDateL = Utilities.convertStringToLong(startDate);
        Long endDateL = Utilities.convertStringToLong(endDate);
        List<Object[]> statisticRequests = requestRepository.statisticRequest(startDateL, endDateL);
        List<StatisticData> data = Utilities.getListBetweenDate(startDate, endDate);
        for (Object[] item : statisticRequests) {
            for (StatisticData d : data) {
                if (d.getDateTime().equals(item[0])) {
                    d.setTotal( item[1]);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", data));
    }
}
