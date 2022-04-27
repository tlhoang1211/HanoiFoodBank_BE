package com.example.hfb.model.dto;

import com.example.hfb.entity.Category;
import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    private Integer id;
    private String name;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer UpdatedBy;
    private int status;

    public static CategoryDTO categoryDTO(Category category){
        CategoryDTO tmp = new CategoryDTO();
        tmp.setId(category.getId());
        tmp.setName(category.getName());
        tmp.setCreatedAt(Utilities.convertLongToDate(category.getCreatedAt()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(category.getUpdatedAt()));
        tmp.setCreatedBy(category.getCreatedBy());
        tmp.setUpdatedBy(category.getUpdatedBy());
        tmp.setStatus(category.getStatus());
        return tmp;
    }
}
