package com.example.hfb.model.dto;

import com.example.hfb.entity.Food;
import com.example.hfb.entity.FoodPro;
import com.example.hfb.repository.FoodRepository;
import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodDTO {
    private FoodRepository foodRepository;
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

    public static FoodDTO foodPro(FoodPro food, FoodRepository foodRepository){
        FoodDTO tmp = new FoodDTO();
        tmp.setId((int) food.getId());
        tmp.setName(food.getName());
        tmp.setAvatar(food.getAvatar());
        tmp.setImages(food.getImages());
        tmp.setQuantity((int) food.getQuantity());
        tmp.setCreatedAt(Utilities.convertLongToDate(food.getCreated_at()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(food.getUpdated_at()));
        tmp.setExpirationDate(Utilities.convertLongToDate(food.getExpiration_date()));
        tmp.setCreatedBy((int) food.getCreated_by());
        tmp.setCategoryId((int) food.getCategory_id());
        tmp.setUpdatedBy((int) food.getUpdated_by());
        tmp.setStatus((int) food.getStatus());
        tmp.setDescription(food.getDescription());
        tmp.setContent(food.getContent());
        Food f = foodRepository.findById((int) food.getCategory_id()).orElse(null);
        tmp.setCategory(f != null ? f.getName() : "");
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
