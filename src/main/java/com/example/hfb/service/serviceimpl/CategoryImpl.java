package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.Category;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.CategoryDTO;
import com.example.hfb.repository.CategoryRepository;
import com.example.hfb.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Service
@Slf4j
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<ResponseData> save(Integer userId, String name) {
        List<Category> categories = categoryRepository.findByName(name);
        if (categories.size() > 0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "Category name already", "")
            );
        }
        Category category = new Category(userId, name);
        CategoryDTO categoryDTO = CategoryDTO.categoryDTO(categoryRepository.save(category));
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Insert successfully", categoryDTO));
    }

    @Override
    public ResponseEntity<ResponseData> update(Category model, int id) {
        Category categoryUpdate = categoryRepository.findById(id).map(category -> {
            category.setName(model.getName());
            category.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            category.setStatus(model.getStatus());
            category.setUpdatedBy(model.getUpdatedBy());
            return categoryRepository.save(category);
        }).orElseGet(() -> categoryRepository.save(model));
        CategoryDTO categoryDTO = CategoryDTO.categoryDTO(categoryUpdate);
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully", categoryDTO));
    }

    @Override
    public ResponseEntity<ResponseData> findByIdCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            CategoryDTO categoryDTO = CategoryDTO.categoryDTO(category.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseData(HttpStatus.OK.value(), "Successfully", categoryDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find category with id " + id, ""));
        }
    }

    @Override
    public ResponseEntity<ResponseData> findAll(String name, int status, int page, String sortBy, int limit, String order) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (order.equals("asc")){
            direction = Sort.Direction.ASC;
        }
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
        Page<Category> categories = categoryRepository.findAll(name, status, pageable);
        Page<CategoryDTO> dtoPage = categories.map(new Function<Category, CategoryDTO>() {
            @Override
            public CategoryDTO apply(Category category) {
                CategoryDTO dto = CategoryDTO.categoryDTO(category);
                return dto;
            }
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", dtoPage));
    }
}
