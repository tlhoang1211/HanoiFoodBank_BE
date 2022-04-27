package com.example.hfb.service;

import com.example.hfb.entity.Category;
import com.example.hfb.model.ResponseData;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<ResponseData> save (Integer userId, String name);
    ResponseEntity<ResponseData> update (Category model, int id);
    ResponseEntity<ResponseData> findByIdCategory (int id);
    ResponseEntity<ResponseData> findAll (String name, int status, int page, String sortBy, int limit, String order);
}
