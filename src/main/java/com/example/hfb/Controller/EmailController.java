package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.model.dto.ClientSdi;
import com.example.hfb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_MAIL)
@CrossOrigin(origins = "*")
public class EmailController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/send")
    public ResponseEntity<Boolean> create (@RequestBody ClientSdi clientSdi){
        return ResponseEntity.ok(clientService.create(clientSdi));
    }
}
