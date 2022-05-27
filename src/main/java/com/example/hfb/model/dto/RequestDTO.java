package com.example.hfb.model.dto;

import com.example.hfb.entity.Food;
import com.example.hfb.entity.Request;
import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {
    private Integer userId;
    private Integer foodId;
    private int supplierId;
    private String supplierName;
    private String message;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private FoodDTO foodDTO;
    private int status;

    public static RequestDTO requestDTO (Request request){
        RequestDTO tmp = new RequestDTO();
        tmp.setUserId(request.getId().getUserId());
        tmp.setFoodId(request.getId().getFoodId());
        tmp.setSupplierId(request.getSupplierId());
        tmp.setSupplierName(request.getSupplierName());
        tmp.setCreatedAt(Utilities.convertLongToDate(request.getCreatedAt()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(request.getUpdatedAt()));
        tmp.setCreatedBy(request.getCreatedBy());
        tmp.setUpdatedBy(request.getUpdatedBy());
        tmp.setStatus(request.getStatus());
        tmp.setMessage(request.getMessage());
        tmp.setFoodDTO(FoodDTO.foodDTO(request.getFood()));
        return tmp;
    }
}
