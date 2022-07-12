package com.example.hfb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackModel {
    private Integer id;
    private String image;
    private String content;
    private Integer rate;
    private Integer type;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private Integer status;
    private Integer userId;
}
