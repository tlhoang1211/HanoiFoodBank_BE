package com.example.hfb.service;

import com.example.hfb.model.ResponseData;
import org.springframework.http.ResponseEntity;

public interface DonationService {
    ResponseEntity<ResponseData> findById (Integer id);
    ResponseEntity<ResponseData> findAll (String name,
                                          String phone,
                                          Double amount,
                                          String startCreated,
                                          String endCreated,
                                          int status,
                                          int page,
                                          String sortBy,
                                          int limit,
                                          String order);
}
