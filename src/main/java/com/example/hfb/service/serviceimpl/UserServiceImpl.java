package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.*;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.RoleDTO;
import com.example.hfb.model.dto.UserDTO;
import com.example.hfb.model.UserModel;
import com.example.hfb.repository.RoleRepository;
import com.example.hfb.repository.UserRepository;
import com.example.hfb.repository.UserRoleRepository;
import com.example.hfb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

import static com.example.hfb.config.UrlConfig.ROLE_USER;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<ResponseData> saveUser(UserModel model) {
        User u =  userRepository.findByUsername(model.getUsername());
        if (u != null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED.value()).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "username already in database", ""));
        }
        User newUser = new User(
                model.getName(),
                model.getUsername(),
                model.getPassword(),
                model.getPhone(),
                model.getAddress(),
                model.getAvatar(),
                model.getPositionLongitude(),
                model.getPositionLatitude()
        );
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        newUser.setCreatedBy(newUser.getId());
        newUser.setUpdatedBy(newUser.getId());
        UserDTO userDTO = UserDTO.userDTO(userRepository.save(newUser));
        Role role = roleRepository.findByName(ROLE_USER);
        userRoleRepository.save(new UserRole(new UserRoleKey(newUser.getId(), role.getId()), newUser, role));
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseData(HttpStatus.OK.value(), "save success", userDTO));
    }

    @Override
    public ResponseEntity<ResponseData> updateUser(UserModel model, Integer id) {
        User userUpdate = userRepository.findById(id).orElse(null);
        if (userUpdate == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "cannot user in database", ""));
        }
        User u =  userRepository.findByUsername(model.getUsername());
        if (u != null && (userUpdate.getUsername() != u.getUsername())){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED.value()).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "username already in database", ""));
        }
        userUpdate.setName(model.getName());
        userUpdate.setUsername(model.getUsername());
        userUpdate.setEmail(model.getUsername());
        userUpdate.setPhone(model.getPhone());
        userUpdate.setAddress(model.getAddress());
        userUpdate.setAvatar(model.getAvatar());
        userUpdate.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        userUpdate.setUpdatedBy(model.getUpdatedBy());
        userUpdate.setStatus(model.getStatus());

        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseData(HttpStatus.OK.value(), "Update success", UserDTO.userDTO(userRepository.save(userUpdate))));
    }

    @Override
    public ResponseEntity<ResponseData> updatePoint(UserModel model, Integer id) {
        User userUpdate = userRepository.findById(id).orElse(null);
        if (userUpdate == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "cannot user in database", ""));
        }
        userUpdate.setPointEvaluation(model.getPointEvaluation());
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseData(HttpStatus.OK.value(), "Update success", UserDTO.userDTO(userRepository.save(userUpdate))));
    }

    @Override
    public ResponseEntity<ResponseData> updateListStatus(Integer status, List<Integer> userId, Integer updateBy) {
        List<User> users = userRepository.findAllById(userId);
        for (User user: users) {
            user.setStatus(status);
            user.setUpdatedBy(updateBy);
        }
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> usersUpdate = userRepository.saveAll(users);
        for (User user: usersUpdate) {
            userDTOS.add(UserDTO.userDTO(user));
        }
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully", userDTOS));
    }

    @Override
    public ResponseEntity<ResponseData> changePassword(String password, Integer id) {
        User userUpdate = userRepository.findById(id).orElse(null);
        if (userUpdate == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "cannot user in database", ""));
        }
        userUpdate.setPassword(bCryptPasswordEncoder.encode(password));
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseData(HttpStatus.OK.value(), "Change password success", UserDTO.userDTO(userRepository.save(userUpdate))));
    }

    @Override
    public RoleDTO saveRole(Role role) {
        if (roleRepository.findByName(role.getName()) != null) {
            return null;
        }
        return RoleDTO.roleDTO(roleRepository.save(role));
    }

    @Override
    public ResponseEntity<ResponseData> addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "cannot user in database", ""));
        } else if (role == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "cannot role in database", ""));
        }
        UserRoleKey userRoleKey = new UserRoleKey(user.getId(), role.getId());
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
        if (userRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED.value()).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "User_Role already", ""));
        } else {
            userRoleRepository.save(new UserRole(new UserRoleKey(user.getId(), role.getId()), user, role));
            return ResponseEntity.status(HttpStatus.OK.value()).body(
                    new ResponseData(HttpStatus.OK.value(), "Success", ""));
        }
    }

    @Override
    public UserDTO getUser(String username) {
        User u =  userRepository.findByUsername(username);
        if (u == null){
            return null;
        }
        UserDTO userDTO = UserDTO.userDTO(userRepository.findByUsername(username));
        return userDTO;
    }

    @Override
    public ResponseEntity<ResponseData> getUserById(Integer id) {
        Optional<User> user =  userRepository.findById(id);
        if (user.isPresent()) {
            UserDTO userDTO = UserDTO.userDTO(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseData(HttpStatus.OK.value(), "Successfully", userDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find user with id " + id, ""));
        }
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
        Role roleExist = roleRepository.findByName(role);
        if (roleExist == null) {
            return null;
        }
        List<UserRole> userRoles = userRoleRepository.findByRole(roleExist);
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserRole ur : userRoles) {
            userDTOList.add(UserDTO.userDTO(ur.getUser()));
        }
        return userDTOList;
    }

    @Override
    public List<RoleDTO> getRoles(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the db");
        } else {
            log.info("User founded in db");
        }
        Set<UserRole> userRoles =  user.getUserRoles();
        List<RoleDTO> roles =  new ArrayList<>();
        for (UserRole ur : userRoles) {
            roles.add(RoleDTO.roleDTO(ur.getRole()));
        }
        return roles;
    }

    @Override
    public ResponseEntity<ResponseData> getUserRole(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        UserRoleKey userRoleKey = new UserRoleKey(user.getId(), role.getId());
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
        if (userRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK.value()).body(
                    new ResponseData(HttpStatus.OK.value(), "Successfully", UserDTO.userDTO(user)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Fail", ""));
        }
    }

    @Override
    public Iterable<UserDTO> search(String keyword, Pageable pageable) {
        Page<User> users = userRepository.search(keyword, pageable);
        Page<UserDTO> dtoPage = users.map(new Function<User, UserDTO>() {
            @Override
            public UserDTO apply(User user) {
                return UserDTO.userDTO(user);
            }
        });
        return  dtoPage;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        String un = user.getName();
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the db");
        } else {
            log.info("User founded in db");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<UserRole> userRoles =  user.getUserRoles();
        Set<Role> roles =  new HashSet<>();
        for (UserRole ur : userRoles) {
            roles.add(ur.getRole());
        }
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
