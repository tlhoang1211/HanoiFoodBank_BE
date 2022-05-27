package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.model.FoodModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.service.DonationService;
import com.example.hfb.service.FoodService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_DONATION)
@CrossOrigin(origins = "*")
public class DonationController {
    @Autowired
    private DonationService donationService;

    @GetMapping("/search")
    public ResponseEntity<ResponseData> search (
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "phone", required = false, defaultValue = "") String phone,
            @RequestParam(name = "amount", required = false, defaultValue = "-1") Double amount,
            @RequestParam(name = "startCreated", required = false, defaultValue = "") String startCreated,
            @RequestParam(name = "endCreated", required = false, defaultValue = "") String endCreated,
            @RequestParam(name = "status", required = false, defaultValue = "-1") int status,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "limit", required = false, defaultValue = "0") int limit,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {

        return donationService.findAll(name, phone, amount, startCreated, endCreated, status, page, sortBy, limit, order);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> findById (@PathVariable Integer id){
        return donationService.findById(id);
    }
}



