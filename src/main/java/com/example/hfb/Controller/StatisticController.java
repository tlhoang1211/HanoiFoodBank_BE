package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.model.FeedbackModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.service.StatisticService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_STATISTIC)
@CrossOrigin(origins = "*")
public class StatisticController {
    @Autowired
    private StatisticService service;

    @PostMapping("/donation")
    public ResponseEntity<ResponseData> dataDonation (@RequestBody DateRange model){
        return service.statisticDonation(model.getStartDate(), model.getEndDate());
    }

    @PostMapping("/food")
    public ResponseEntity<ResponseData> dataFood (@RequestBody DateRange model){
        return service.statisticFood(model.getStartDate(), model.getEndDate());
    }

    @PostMapping("/request")
    public ResponseEntity<ResponseData> dataRequest (@RequestBody DateRange model){
        return service.statisticRequest(model.getStartDate(), model.getEndDate());
    }
}

@Data
class DateRange {
    private String startDate;
    private String endDate;
}
