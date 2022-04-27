package com.example.hfb.service;

import com.example.hfb.model.dto.DataMailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dto, String templateName) throws MessagingException;
}
