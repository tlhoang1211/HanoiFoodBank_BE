package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.*;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.RoleDTO;
import com.example.hfb.model.dto.UserDTO;
import com.example.hfb.repository.*;
import com.example.hfb.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SeedingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public UserDTO saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return UserDTO.userDTO(userRepository.save(user));
    }


    public void deleteCategories() {
        categoryRepository.deleteAll();
    }
    public Category findByCategoryId(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public void saveFood(Food food) {
        foodRepository.save(food);
    }
    public void saveFeedback(Integer id, String image, String content, int rate, int type, Integer toUserId, Integer createdBy) {
        User user = userRepository.findById(toUserId).orElse(null);
        Feedback feedback = new Feedback(
                id,
                image,
                content,
                rate,
                type,
                createdBy,
                user,
                toUserId
        );
        feedbackRepository.save(feedback);
    }
    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }
    public void saveRequest(Integer userId, Integer foodId, String message, String createdAt, String updatedAt) {
        User user = userRepository.findById(userId).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);
        UserFoodKey userFoodKey = new UserFoodKey(userId, foodId);
        User supplier = userRepository.findById(food.getCreatedBy()).orElse(null);
        Request request = new Request(
                userFoodKey,
                user,
                food,
                supplier.getId(),
                supplier.getName(),
                message,
                Utilities.convertStringToLong(createdAt),
                Utilities.convertStringToLong(updatedAt),
                userId,
                userId,
                3
        );
        requestRepository.save(request);
    }
    public void findByIdUser(Integer id) {
        userRepository.findById(id);
    }
    public void findByIdFood(Integer id) {
        foodRepository.findById(id);
    }
    public void deleteFoods() {
        foodRepository.deleteAll();
    }
    public void deleteRequests() {
        requestRepository.deleteAll();
    }
//    public void resetIdFood() {
//        foodRepository.resetId();
//    }
//    public void resetIdCategory() {
//        categoryRepository.resetId();
//    }
    public void saveCategory(Integer id, Integer userId, String name) {
        categoryRepository.save(new Category(id, userId, name));
    }
    public void deleteUserRoles() {
        userRoleRepository.deleteAll();
    }
//    public void resetIdUserRole() {
//        userRoleRepository.resetId();
//    }
    public void deleteUsers() {
        userRepository.deleteAll();
    }
//    public void resetIdUser() {
//        userRepository.resetId();
//    }
    public void deleteRoles() {
        roleRepository.deleteAll();
    }
//    public void resetIdRole() {
//        roleRepository.resetId();
//    }
    public RoleDTO saveRole(Role role) {
        return RoleDTO.roleDTO(roleRepository.save(role));
    }
    public ResponseEntity<ResponseData> addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "cannot found this user in database", ""));
        } else if (role == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "cannot found this role in database", ""));
        }
        userRoleRepository.save(new UserRole(new UserRoleKey(user.getId(), role.getId()), user, role));
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseData(HttpStatus.OK.value(), "Success", ""));
    }
}
