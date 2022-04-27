package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.FoodModel;
import com.example.hfb.model.dto.LoginDTOModel;
import com.example.hfb.service.FoodService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_FOOD)
@CrossOrigin(origins = "*")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @PostMapping()
    public ResponseEntity<ResponseData> save (@RequestBody FoodModel model){
        return foodService.save(model);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseData> search (
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "categoryId", required = false, defaultValue = "-1") Integer categoryId,
            @RequestParam(name = "createdBy", required = false, defaultValue = "-1") Integer createdBy,
            @RequestParam(name = "updatedBy", required = false, defaultValue = "-1") Integer updatedBy,
            @RequestParam(name = "expirationDate", required = false, defaultValue = "") String expirationDate,
            @RequestParam(name = "startCreated", required = false, defaultValue = "") String startCreated,
            @RequestParam(name = "endCreated", required = false, defaultValue = "") String endCreated,
            @RequestParam(name = "startUpdated", required = false, defaultValue = "") String startUpdated,
            @RequestParam(name = "endUpdated", required = false, defaultValue = "") String endUpdated,
            @RequestParam(name = "status", required = false, defaultValue = "-1") int status,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "limit", required = false, defaultValue = "0") int limit,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {

        return foodService.findAll(name, categoryId, createdBy, updatedBy, expirationDate, startCreated, endCreated, startUpdated, endUpdated, status, page, sortBy, limit, order);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseData> update (@RequestBody FoodModel model, @PathVariable Integer id){
        return foodService.update(model, id);
    }

    @PostMapping("/status/{id}")
    public ResponseEntity<ResponseData> updateStatus (@RequestBody FoodModel model, @PathVariable Integer id){
        return foodService.updateStatus(model.getStatus(), id, model.getUpdatedBy());
    }

    @PostMapping("/update-list-status")
    public ResponseEntity<ResponseData> updateListStatus (@RequestBody FoodModel model){
        String arr = model.getArrId();
        Type IdListType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> Ids = new Gson().fromJson(arr, IdListType);
        return foodService.updateListStatus(model.getStatus(), Ids, model.getUpdatedBy());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> findById (@PathVariable Integer id){
        return foodService.findById(id);
    }
    @GetMapping("/scan")
    public ResponseEntity<ResponseData> scan (){
        return foodService.scanExpirationDate();
    }
}



