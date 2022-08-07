package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.entity.Request;
import com.example.hfb.entity.UserFoodKey;
import com.example.hfb.model.FoodModel;
import com.example.hfb.model.RequestModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.repository.RequestRepository;
import com.example.hfb.service.RequestService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_REQUEST)
@CrossOrigin(origins = "*")
public class RequestController {
    @Autowired
    private RequestService service;

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping()
    public ResponseEntity<ResponseData> save (@RequestBody RequestModel model){
        return service.saveRequest(model);
    }

    @GetMapping("/{userId}/{foodId}")
    public ResponseEntity<ResponseData> getRequest (@PathVariable int userId, @PathVariable int foodId){
        return service.getRequest(userId, foodId);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> search (
            @RequestParam(name = "userId", required = false, defaultValue = "-1") int userId,
            @RequestParam(name = "foodId", required = false, defaultValue = "-1") int foodId,
            @RequestParam(name = "startCreated", required = false, defaultValue = "") String startCreated,
            @RequestParam(name = "endCreated", required = false, defaultValue = "") String endCreated,
            @RequestParam(name = "startUpdated", required = false, defaultValue = "") String startUpdated,
            @RequestParam(name = "endUpdated", required = false, defaultValue = "") String endUpdated,
            @RequestParam(name = "status", required = false, defaultValue = "-1") int status,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "limit", required = false, defaultValue = "0") int limit,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {

        return service.findAll(userId, foodId, startCreated, endCreated, startUpdated, endUpdated, status, page, sortBy, limit, order);
    }

    @PostMapping("/{userId}/{foodId}")
    public ResponseEntity<ResponseData> update (@RequestBody RequestModel model, @PathVariable Integer userId, @PathVariable Integer foodId){
        return service.update(model, userId, foodId);
    }

    @PostMapping("/status/{userId}/{foodId}")
    public ResponseEntity<ResponseData> updateStatus (@RequestBody RequestModel model, @PathVariable Integer userId, @PathVariable Integer foodId){
        return service.updateStatus(model, userId, foodId);
    }

    @PostMapping("/update-list-status")
    public ResponseEntity<ResponseData> updateListStatus (@RequestBody RequestModel model){
        return service.updateListStatus(model);
    }
}
