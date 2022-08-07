package com.example.hfb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseData {
    private int status;
    private String message;
    private Object data;
    private Object option;

    public ResponseData(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
