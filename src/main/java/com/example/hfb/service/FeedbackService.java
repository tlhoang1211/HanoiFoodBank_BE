package com.example.hfb.service;

import com.example.hfb.model.FeedbackModel;
import com.example.hfb.model.ResponseData;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {
    ResponseEntity<ResponseData> save (FeedbackModel model);
    ResponseEntity<ResponseData> update (FeedbackModel model, Integer id);
    ResponseEntity<ResponseData> findById (Integer id);
    ResponseEntity<ResponseData> findAll (Integer type,
                                          Integer status,
                                          Integer createdBy,
                                          Integer userId,
                                          Integer foodId,
                                          Integer startRate,
                                          Integer endRate,
                                          int page,
                                          String sortBy,
                                          int limit,
                                          String order);
}
