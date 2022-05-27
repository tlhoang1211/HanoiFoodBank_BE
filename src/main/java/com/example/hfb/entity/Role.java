package com.example.hfb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private long createdAt;
    private long updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<UserRole> userRoles = new HashSet<>();

    public Role(Integer id, String name) {
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = id;
        this.updatedBy = id;
        this.status = 1;
        this.id = id;
        this.name = name;
    }

    public Role(String name, Integer createdBy) {
        this.name = name;
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.status = 1;
    }
}
