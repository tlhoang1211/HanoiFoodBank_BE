package com.example.hfb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleModel {
    private Integer id;
    private String name;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;
}
