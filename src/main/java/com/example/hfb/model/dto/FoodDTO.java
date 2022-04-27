package com.example.hfb.model.dto;

import com.example.hfb.entity.Food;
import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodDTO {
    private Integer id;
    private String name;
    private String avatar;
    private String images;
    private String description;
    private String content;
    private int quantity;
    private String expirationDate;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer UpdatedBy;
    private int categoryId;
    private int status;
    private String category;
    private String supplierName;
    private String supplierEmail;

    public static FoodDTO foodDTO(Food food){
        FoodDTO tmp = new FoodDTO();
        tmp.setId(food.getId());
        tmp.setName(food.getName());
        tmp.setAvatar(food.getAvatar());
        tmp.setImages(food.getImages());
        tmp.setQuantity(food.getQuantity());
        tmp.setCreatedAt(Utilities.convertLongToDate(food.getCreatedAt()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(food.getUpdatedAt()));
        tmp.setExpirationDate(Utilities.convertLongToDate(food.getExpirationDate()));
        tmp.setCreatedBy(food.getCreatedBy());
        tmp.setCategoryId(food.getCategoryId());
        tmp.setUpdatedBy(food.getUpdatedBy());
        tmp.setStatus(food.getStatus());
        tmp.setDescription(food.getDescription());
        tmp.setContent(food.getContent());
        tmp.setCategory(food.getCategory().getName());
        return tmp;
    }

    public static FoodDTO foodDTO(Food food, String supplierName, String supplierEmail){
        FoodDTO tmp = new FoodDTO();
        tmp.setId(food.getId());
        tmp.setName(food.getName());
        tmp.setAvatar(food.getAvatar());
        tmp.setImages(food.getImages());
        tmp.setQuantity(food.getQuantity());
        tmp.setCreatedAt(Utilities.convertLongToDate(food.getCreatedAt()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(food.getUpdatedAt()));
        tmp.setExpirationDate(Utilities.convertLongToDate(food.getExpirationDate()));
        tmp.setCreatedBy(food.getCreatedBy());
        tmp.setCategoryId(food.getCategoryId());
        tmp.setUpdatedBy(food.getUpdatedBy());
        tmp.setStatus(food.getStatus());
        tmp.setDescription(food.getDescription());
        tmp.setContent(food.getContent());
        tmp.setCategory(food.getCategory().getName());
        tmp.setSupplierName(supplierName);
        tmp.setSupplierEmail(supplierEmail);
        return tmp;
    }
}
