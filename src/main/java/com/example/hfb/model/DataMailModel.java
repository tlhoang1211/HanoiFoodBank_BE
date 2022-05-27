package com.example.hfb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMailModel {
    private String foodName;
    private String description;
    private String urlDetail;
    private String urlImage;
}
