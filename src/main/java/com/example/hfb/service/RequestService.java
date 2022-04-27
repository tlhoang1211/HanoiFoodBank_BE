package com.example.hfb.service;

import com.example.hfb.model.FoodModel;
import com.example.hfb.model.RequestModel;
import com.example.hfb.model.ResponseData;
import org.springframework.http.ResponseEntity;

public interface RequestService {
    ResponseEntity<ResponseData> saveRequest(RequestModel model);
    ResponseEntity<ResponseData> update (RequestModel model, int userId, int foodId);
    ResponseEntity<ResponseData> updateStatus (RequestModel model, Integer userId, Integer foodId);
    ResponseEntity<ResponseData> updateListStatus (RequestModel model);
    ResponseEntity<ResponseData> getRequest(int userId, int foodId);
    ResponseEntity<ResponseData> findAll (int userId,
                                          int foodId,
                                          String startCreated,
                                          String endCreated,
                                          String startUpdated,
                                          String endUpdated,
                                          int status,
                                          int page,
                                          String sortBy,
                                          int limit,
                                          String order);
}
