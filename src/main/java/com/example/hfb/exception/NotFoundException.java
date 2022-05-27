package com.example.hfb.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) { // định nghĩa message trả về khi có lỗi cho exception
        super(message);
    }
}
