package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.model.FeedbackModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UrlConfig.END_FEEDBACK)
@CrossOrigin(origins = "*")
public class FeedbackController {
    @Autowired
    private FeedbackService service;

    @PostMapping("")
    public ResponseEntity<ResponseData> save(@RequestBody FeedbackModel model){
        return service.save(model);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseData> update (@RequestBody FeedbackModel model, @PathVariable Integer id){
        return service.update(model, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> findById (@PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseData> search (
            @RequestParam(name = "type", required = false, defaultValue = "-1") Integer type,
            @RequestParam(name = "status", required = false, defaultValue = "-1") int status,
            @RequestParam(name = "createdBy", required = false, defaultValue = "-1") Integer createdBy,
            @RequestParam(name = "userId", required = false, defaultValue = "-1") Integer userId,
            @RequestParam(name = "requestId", required = false, defaultValue = "-1") Integer requestId,
            @RequestParam(name = "startRate", required = false, defaultValue = "-1") int startRate,
            @RequestParam(name = "endRate", required = false, defaultValue = "-1") int endRate,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "limit", required = false, defaultValue = "0") int limit,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {

        return service.findAll(type, status, createdBy, userId, requestId, startRate, endRate, page, sortBy, limit, order);
    }
}
