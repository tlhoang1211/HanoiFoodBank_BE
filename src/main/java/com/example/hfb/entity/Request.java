package com.example.hfb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @EmbeddedId
    private UserFoodKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;

    private int supplierId;
    private String supplierName;
    private String message;
    private long createdAt;
    private long updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;

    public Request(UserFoodKey id, User user, Food food, int supplierId, String supplierName, String message) {
        this.id = id;
        this.user = user;
        this.food = food;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.message = message;
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = id.getUserId();
        this.updatedBy = id.getUserId();
        this.status = 1;
    }

    public Request(UserFoodKey id, User user, Food food, int supplierId, String supplierName, String message, long createdAt, long updatedAt, Integer createdBy, Integer updatedBy) {
        this.id = id;
        this.user = user;
        this.food = food;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.status = 3;
    }
}
