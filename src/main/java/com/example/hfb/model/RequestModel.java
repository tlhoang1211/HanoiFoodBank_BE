package com.example.hfb.model;

import com.example.hfb.entity.UserFoodKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestModel {
    private int userId;
    private int foodId;
    private int supplierId;
    private String supplierName;
    private String message;
    private Integer updatedBy;
    private int status;
    private List<UserFoodKey> userFoodKeys;
}
