package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.Role;
import com.example.hfb.model.*;
import com.example.hfb.model.dto.RoleDTO;
import com.example.hfb.repository.RoleRepository;
import com.example.hfb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.function.Function;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public ResponseEntity<ResponseData> save(RoleModel model) {
        Role role = repository.findByName(model.getName());
        if (role != null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "Role name already", "")
            );
        }

        Role newRole = new Role(model.getName(), model.getCreatedBy());
        RoleDTO roleDTO = RoleDTO.roleDTO(repository.save(newRole));
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Insert successfully", roleDTO));
    }

    @Override
    public ResponseEntity<ResponseData> update(RoleModel model, int id) {
        Role roleUpdate = repository.findById(id).map(role -> {
            role.setName(model.getName());
            role.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            role.setStatus(model.getStatus());
            role.setUpdatedBy(model.getUpdatedBy());
            return repository.save(role);
        }).orElseGet(() -> repository.save(new Role(model.getName(), model.getCreatedBy())));
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully",  RoleDTO.roleDTO(roleUpdate)));
    }

    @Override
    public ResponseEntity<ResponseData> findById(int id) {
        Optional<Role> role = repository.findById(id);
        if (role.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseData(HttpStatus.OK.value(), "Successfully", RoleDTO.roleDTO(role.get())));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find role with id " + id, ""));
        }
    }

    @Override
    public ResponseEntity<ResponseData> findAll(String name, int status, int page, String sortBy, int limit, String order) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (order.equals("asc")){
            direction = Sort.Direction.ASC;
        }
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
        Page<Role> roles = repository.findAll(name, status, pageable);
        Page<RoleDTO> dtoPage = roles.map(new Function<Role, RoleDTO>() {
            @Override
            public RoleDTO apply(Role role) {
                RoleDTO dto = RoleDTO.roleDTO(role);
                return dto;
            }
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", dtoPage));
    }
}
