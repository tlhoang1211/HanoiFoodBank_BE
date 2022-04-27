package com.example.hfb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodModel {
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
    private Integer updatedBy;
    private int status;
    private Integer categoryId;
    private String arrId;
}
