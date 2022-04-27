package com.example.hfb.service;

import com.example.hfb.model.ResponseData;
import com.example.hfb.model.FoodModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FoodService {
    ResponseEntity<ResponseData> scanExpirationDate ();
    ResponseEntity<ResponseData> save (FoodModel food);
    ResponseEntity<ResponseData> update (FoodModel food, int id);
    ResponseEntity<ResponseData> updateStatus (Integer status, Integer id, Integer updateBy);
    ResponseEntity<ResponseData> updateListStatus (Integer status, List<Integer> idFood, Integer updateBy);
    ResponseEntity<ResponseData> findById (int id);
    ResponseEntity<ResponseData> findAll (String name,
                                          Integer categoryId,
                                          Integer createdBy,
                                          Integer updatedBy,
                                          String expirationDate,
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
