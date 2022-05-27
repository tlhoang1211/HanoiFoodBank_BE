package com.example.hfb.service;


import com.example.hfb.model.ResponseData;
import com.example.hfb.model.RoleModel;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<ResponseData> save (RoleModel model);
    ResponseEntity<ResponseData> update (RoleModel model, int id);
    ResponseEntity<ResponseData> findById (int id);
    ResponseEntity<ResponseData> findAll (String name, int status, int page, String sortBy, int limit, String order);
}
