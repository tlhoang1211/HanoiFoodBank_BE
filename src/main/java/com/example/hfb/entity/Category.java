package com.example.hfb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private long createdAt;
    private long updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Food> foods = new HashSet<>();

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
        for(Food f : foods) {
            f.setCategory(this);
        }
    }

    public Category(Integer userId, String name) {
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = userId;
        this.updatedBy = userId;
        this.status = 1;
        this.name = name;
    }
    public Category(Integer id, Integer userId, String name) {
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = userId;
        this.updatedBy = userId;
        this.id = id;
        this.status = 1;
        this.name = name;
    }

    public Category(String name, Integer updatedBy, int status) {
        this.name = name;
        this.updatedBy = updatedBy;
        this.status = status;
    }
}
