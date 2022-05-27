package com.example.hfb.service;


import com.example.hfb.model.dto.ClientSdi;

public interface ClientService {
    Boolean create(ClientSdi sdi);
    void createMail(ClientSdi sdi);
}
