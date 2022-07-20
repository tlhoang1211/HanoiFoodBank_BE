package com.example.hfb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String avatar;
    @Column(name = "images", columnDefinition = "TEXT")
    private String images;
    private String description;
    private String content;
    private int quantity;
    private long expirationDate;
    private long createdAt;
    private long updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;

    @OneToOne(mappedBy = "food")
    private Feedback feedback;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<Request> requests = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "categoryId", nullable = false, referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;

    @Column(insertable = false, updatable = false)
    private int categoryId;

    public Food(Integer id, String name, String avatar, String images, int quantity, long expirationDate, int categoryId) {
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = id;
        this.updatedBy = id;
        this.status = 1;
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.images = images;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.categoryId = categoryId;
    }
    public Food(Integer id, String name, String avatar, String images, int quantity, long expirationDate, int categoryId , Category category) {
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = id;
        this.updatedBy = id;
        this.status = 1;
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.images = images;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.categoryId = categoryId;
        this.category = category;
    }

    public Food(String name, String avatar, String images, int quantity, long expirationDate, Integer createdBy, int categoryId, String description, String content, Category category) {
        this.name = name;
        this.avatar = avatar;
        this.images = images;
        this.description = description;
        this.content = content;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.status = 1;
        this.categoryId = categoryId;
        this.category = category;
    }

    public Food(String name, String avatar, String images, int quantity, long expirationDate, Integer createdBy, int categoryId, Category category, String description, String content) {
        this.name = name;
        this.avatar = avatar;
        this.images = images;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.status = 1;
        this.categoryId = categoryId;
        this.category = category;
        this.description = description;
        this.content = content;
    }

    public Food(Integer id, String name, String avatar, String images, String description, String content, long expirationDate, long createdAt, long updatedAt, Integer createdBy, Integer updatedBy, Category category, int categoryId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.images = images;
        this.description = description;
        this.content = content;
        this.quantity = 1;
        this.expirationDate = expirationDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.status = 2;
        this.category = category;
        this.categoryId = categoryId;
    }
}
