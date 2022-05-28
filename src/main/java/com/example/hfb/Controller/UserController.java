package com.example.hfb.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hfb.config.UrlConfig;
import com.example.hfb.model.FoodModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.RoleDTO;
import com.example.hfb.model.dto.UserDTO;
import com.example.hfb.model.UserModel;
import com.example.hfb.service.UserService;
import com.example.hfb.utilities.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = UrlConfig.END_POINT)
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/users/{username}")
    public ResponseEntity<ResponseData> getUser(@PathVariable String username){
        UserDTO userDTO = userService.getUser(username);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                    .body(new ResponseData(HttpStatus.NOT_FOUND.value(), "User is not exist",""));
        }
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "success",userDTO));
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseData> getUserByRole(@RequestParam String role){
        if (userService.getUsersByRole(role) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                    .body(new ResponseData(HttpStatus.NOT_FOUND.value(), "Role is not exist",""));
        }
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "success",userService.getUsersByRole(role)));
    }

    @GetMapping("/users/search")
    public ResponseEntity<ResponseData> search (
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {

        Sort.Direction direction = Sort.Direction.DESC;
        if (order.equals("asc")){
            direction = Sort.Direction.ASC;
        }
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(),
        "success", userService.search(keyword, pageable)));
    }

    @GetMapping("/users/roles")
    public ResponseEntity<ResponseData> getRoles(@RequestParam String username){
        return ResponseEntity.ok(new ResponseData(HttpStatus.OK.value(), "success", userService.getRoles(username)));
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<ResponseData> updateUser(@RequestBody UserModel model, @PathVariable Integer id){
        return userService.updateUser(model, id);
    }

    @PostMapping("/users/point/{id}")
    public ResponseEntity<ResponseData> updatePoint(@RequestBody UserModel model, @PathVariable Integer id){
        return userService.updatePoint(model, id);
    }

    @PostMapping("/users/update-list-status")
    public ResponseEntity<ResponseData> updateListStatus (@RequestBody FoodModel model){
        String arr = model.getArrId();
        Type IdListType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> Ids = new Gson().fromJson(arr, IdListType);
        return userService.updateListStatus(model.getStatus(), Ids, model.getUpdatedBy());
    }

    @PostMapping("/users/change-password/{id}")
    public ResponseEntity<ResponseData> changePassword(@RequestBody UserModel model, @PathVariable Integer id){
        return userService.changePassword(model.getPassword(), id);
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseData> saveUser(@RequestBody UserModel user){
        return userService.saveUser(user);
    }

    @PostMapping("/roles/add-to-user")
    public ResponseEntity<ResponseData> addRoleToUser(@RequestBody RoleToUserForm form){
       return userService.addRoleToUser(form.getUsername(), form.getRoleName());
    }

    @PostMapping("/get-user-role")
    public ResponseEntity<ResponseData> getUserRole(@RequestBody RoleToUserForm form){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/hfb/get-user-role").toUriString());
        return userService.getUserRole(form.getUsername(), form.getRoleName());
    }

    @CrossOrigin
    @GetMapping("/token/refresh")
    public ResponseEntity<ResponseData> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserDTO user = userService.getUser(username);

                String access_token = JWT.create().withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userService.getRoles(username).stream().map(RoleDTO::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                refresh_token = JWT.create().withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*10000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Utilities.responseDataToken(response, refresh_token, access_token);
            } catch (Exception ex) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", HttpStatus.FORBIDDEN.value());
                data.put("message", ex.getMessage());
                new ObjectMapper().writeValue(response.getOutputStream(), data);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED.value()).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "Fail", new RuntimeException("Refresh token is missing")));
        }
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseData(HttpStatus.OK.value(), "Success", new RuntimeException("Refresh token is missing")));
    }

}
@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
