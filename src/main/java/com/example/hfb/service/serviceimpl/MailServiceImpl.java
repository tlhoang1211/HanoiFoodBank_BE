package com.example.hfb.service.serviceimpl;

import com.example.hfb.model.dto.DataMailDTO;
import com.example.hfb.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void sendHtmlMail(DataMailDTO dto, String templateName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

        Context context = new Context();
        context.setVariables(dto.getProps());

        String html = templateEngine.process(templateName, context);

        helper.setTo(dto.getTo().toArray(new String[dto.getTo().size()]));
        helper.setSubject(dto.getSubject());
        helper.setText(html, true);

        mailSender.send(message);
    }
}
