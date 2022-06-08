package com.example.hfb.model.dto;

import com.example.hfb.entity.User;
import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String username;
    private String avatar;
    private String email;
    private String phone;
    private String address;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int pointEvaluation;
    private float positionLatitude;
    private float positionLongitude;
    private int status;

    public static UserDTO userDTO(User user){
        UserDTO tmp = new UserDTO();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setUsername(user.getUsername());
        tmp.setAvatar(user.getAvatar());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setAddress(user.getAddress());
        tmp.setCreatedAt(Utilities.convertLongToDate(user.getCreatedAt()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(user.getUpdatedAt()));
        tmp.setCreatedBy(user.getCreatedBy());
        tmp.setUpdatedBy(user.getUpdatedBy());
        tmp.setPointEvaluation(user.getPointEvaluation());
        tmp.setPositionLatitude(user.getPositionLatitude());
        tmp.setPositionLongitude(user.getPositionLongitude());
        tmp.setStatus(user.getStatus());
        return tmp;
    }
}
