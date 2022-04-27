package com.example.hfb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMailDTO {
    private List<String> to;
    private String subject;
    private String content;
    private Map<String, Object> props;
}
