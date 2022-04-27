package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.UserRole;
import com.example.hfb.model.dto.ClientSdi;
import com.example.hfb.model.dto.DataMailDTO;
import com.example.hfb.model.dto.UserDTO;
import com.example.hfb.service.ClientService;
import com.example.hfb.service.MailService;
import com.example.hfb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            List<UserDTO> userDTOS = userService.getUsersByRole("ROLE_USER");
            List<String> emails = new ArrayList<>();
            for (UserDTO u : userDTOS) {
                if (sdi.getUserId() == u.getId()) {
                    continue;
                }
                emails.add(u.getEmail());
            }

            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo(emails);
            dataMail.setSubject("Thông tin món đồ ăn mới vừa được cập nhập lên hệ thống.");

            Map<String, Object> props = new HashMap<>();
//            props.put("foodName", sdi.getFoodName());
//            props.put("urlImage", sdi.getUrlImage());
//            props.put("description", sdi.getDescription());
//            props.put("urlDetail", sdi.getUrlDetail());
            props.put("data", sdi.getDataMailModels());
            dataMail.setProps(props);
            mailService.sendHtmlMail(dataMail, "client");
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }

    @Override
    public void createMail(ClientSdi sdi) {
        try {
            List<UserDTO> userDTOS = userService.getUsersByRole("ROLE_USER");
            List<String> emails = new ArrayList<>();
            for (UserDTO u : userDTOS) {
                if (sdi.getUserId() == u.getId()) {
                    continue;
                }
                emails.add(u.getEmail());
            }

            DataMailDTO dataMail = new DataMailDTO();
            dataMail.setTo(emails);
            dataMail.setSubject("Thông tin món đồ ăn mới vừa được cập nhập lên hệ thống.");

            Map<String, Object> props = new HashMap<>();
            props.put("data", sdi.getDataMailModels());
            dataMail.setProps(props);
            mailService.sendHtmlMail(dataMail, "client");
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
    }
}
