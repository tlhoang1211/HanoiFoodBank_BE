package com.example.hfb.Controller;

import com.example.hfb.config.UrlConfig;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.RoleModel;
import com.example.hfb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = UrlConfig.END_POINT_ROLE)
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("")
    public ResponseEntity<ResponseData> saveRole(@RequestBody RoleModel model){
        return roleService.save(model);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseData> update (@RequestBody RoleModel model, @PathVariable Integer id){
        return roleService.update(model, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> findById (@PathVariable Integer id){
        return roleService.findById(id);
    }

    @GetMapping()
    public ResponseEntity<ResponseData> search (
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "status", required = false, defaultValue = "-1") int status,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {

        return roleService.findAll(name, status, page, sortBy, limit, order);
    }
}
