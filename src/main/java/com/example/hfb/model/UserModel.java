package com.example.hfb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private int pointEvaluation;
    private double positionLatitude;
    private double positionLongitude;
    private String avatar;
    private String email;
    private String phone;
    private String address;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;
    private String arrId;
}
