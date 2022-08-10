package com.example.hfb.service;

import com.example.hfb.model.ResponseData;
import com.example.hfb.entity.Role;
import com.example.hfb.model.dto.RoleDTO;
import com.example.hfb.model.dto.UserDTO;
import com.example.hfb.model.UserModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<ResponseData> saveUser(UserModel model);
    ResponseEntity<ResponseData> updateUser(UserModel model, Integer id);
    ResponseEntity<ResponseData> updatePoint(UserModel model, Integer id);
    ResponseEntity<ResponseData> updateListStatus (Integer status, List<Integer> userId, Integer updateBy);
    ResponseEntity<ResponseData> changePassword(String password, Integer id);
    RoleDTO saveRole(Role role);
    ResponseEntity<ResponseData> addRoleToUser(String username, String roleName);
    UserDTO getUser(String username);
    ResponseEntity<ResponseData> getUserById(Integer id);
    List<UserDTO> getUsersByRole(String role);
    List<RoleDTO> getRoles(String username);
    ResponseEntity<ResponseData> getUserRole(String username, String roleName);
    Iterable<UserDTO> search(@Param(value="keyword") String keyword, @Param(value="status") int status, Pageable pageable);
    ResponseEntity<ResponseData> updateAccountStatus(Integer status, Integer id);
}
