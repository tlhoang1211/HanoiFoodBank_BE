package com.example.hfb.model.dto;

import com.example.hfb.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private int id;
    private String name;
    //private Set<UserRole> userRoles = new HashSet<>();

    public static RoleDTO roleDTO(Role role){
        RoleDTO tmp = new RoleDTO();
        tmp.setId(role.getId());
        tmp.setName(role.getName());
        //tmp.setUserRoles(role.getUserRoles());
        return tmp;
    }
}
