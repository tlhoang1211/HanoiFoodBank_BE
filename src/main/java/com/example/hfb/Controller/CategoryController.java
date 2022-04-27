package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.entity.Category;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.CategoryModel;
import com.example.hfb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_CATEGORY)
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<ResponseData> search (
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "status", required = false, defaultValue = "-1") int status,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {

        return categoryService.findAll(name, status, page, sortBy, limit, order);
    }

    @PostMapping()
    public ResponseEntity<ResponseData> save (@RequestBody CategoryModel model){
        return categoryService.save(model.getCreatedBy(), model.getName());
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseData> update (@RequestBody CategoryModel model, @PathVariable Integer id){
        Category category = new Category(model.getName(), model.getUpdatedBy(), model.getStatus());
        return categoryService.update(category, id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> findById (@PathVariable Integer id){
        return categoryService.findByIdCategory(id);
    }
}



