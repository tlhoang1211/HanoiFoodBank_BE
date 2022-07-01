package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.*;
import com.example.hfb.model.DataMailModel;
import com.example.hfb.model.FoodModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.ClientSdi;
import com.example.hfb.model.dto.FoodDTO;
import com.example.hfb.repository.*;
import com.example.hfb.service.ClientService;
import com.example.hfb.service.FoodService;
import com.example.hfb.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.example.hfb.config.UrlConfig.END_POINT_CLOUDINARY;
import static com.example.hfb.config.UrlConfig.END_POINT_DETAIL_FOOD;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public ResponseEntity<ResponseData> scanExpirationDate() {
        try {
            List<Food> foods = foodRepository.findAll();
            for (Food item : foods) {
                Long currentTime = Calendar.getInstance().getTimeInMillis();
                if (currentTime > item.getExpirationDate()) {
                    item.setStatus(0);
                    foodRepository.save(item);
                    List<Request> requests = requestRepository.findByFood(item);
                    for (Request r : requests) {
                        if (r.getStatus() != 3) {
                            r.setStatus(4);
                            requestRepository.save(r);
                        }

                    }
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseData(HttpStatus.OK.value(), "Success", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), HttpStatus.NOT_IMPLEMENTED.toString(), ex.getStackTrace()));
        }
    }

    @Override
    public ResponseEntity<ResponseData> save(FoodModel model) {
        if (model.getId() != null) {
            Food f = foodRepository.findById(model.getId()).orElse(null);
            if (f != null) {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "Food name already", "")
                );
            }
        }

        Category category = categoryRepository.findById(model.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find category with id " + category.getId(), ""));
        }
        Food food = new Food(
                model.getName(),
                model.getAvatar(),
                model.getImages(),
                model.getQuantity(),
                Utilities.convertStringToLong(model.getExpirationDate()),
                model.getCreatedBy(),
                model.getCategoryId(),
                model.getDescription(),
                model.getContent(),
                category);

        FoodDTO foodDTO = FoodDTO.foodDTO(foodRepository.save(food));
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Insert successfully", foodDTO));
    }

    @Override
    public ResponseEntity<ResponseData> update(FoodModel model, int id) {
        List<String> errors = new ArrayList<>();
        if (model.getName() == null) {
            errors.add("Name is not empty");
        }
        if (model.getAvatar() == null) {
            errors.add("Avatar is not empty");
        }
        if (model.getImages() == null) {
            errors.add("Images is not empty");
        }
        if (model.getDescription() == null) {
            errors.add("Description is not empty");
        }
        if (model.getContent() == null) {
            errors.add("Content is not empty");
        }
        if (model.getExpirationDate() == null) {
            errors.add("ExpirationDate is not empty");
        }
        if (model.getUpdatedBy() == null) {
            errors.add("UpdatedBy is not empty");
        }
        if (model.getCategoryId() == null) {
            errors.add("CategoryId is not empty or not exist");
        }
        if (errors.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), HttpStatus.NOT_IMPLEMENTED.toString(), errors));
        }
        Category category = categoryRepository.findById(model.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find category with id " + model.getCategoryId(), ""));
        }
        Optional<Food> f = foodRepository.findById(id);
        UserRoleKey userRoleKey = new UserRoleKey(model.getUpdatedBy(), 1);
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
        if (!userRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                    new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot active or delete", ""));
        }
        Food foodUpdate = f.map(food -> {
            food.setName(model.getName());
            food.setAvatar(model.getAvatar());
            food.setImages(model.getImages());
            food.setDescription(model.getDescription());
            food.setContent(model.getContent());
            food.setQuantity(model.getQuantity());
            food.setExpirationDate(Utilities.convertStringToLong(model.getExpirationDate()));
            food.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            food.setStatus(model.getStatus());
            food.setUpdatedBy(model.getUpdatedBy());
            food.setCategoryId(model.getCategoryId());
            food.setCategory(category);
            return foodRepository.save(food);
        }).orElseGet(() -> foodRepository.save(new Food(
                model.getName(),
                model.getAvatar(),
                model.getImages(),
                model.getQuantity(),
                Utilities.convertStringToLong(model.getExpirationDate()),
                model.getCreatedBy(),
                model.getCategoryId(),
                category,
                model.getDescription(),
                model.getContent()
        )));

        FoodDTO foodDTO = FoodDTO.foodDTO(foodUpdate);
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully", foodDTO));
    }

    @Override
    public ResponseEntity<ResponseData> updateStatus(Integer status, Integer id, Integer updateBy) {
        Optional<Food> food = foodRepository.findById(id);
        UserRoleKey userRoleKey = new UserRoleKey(updateBy, 1);
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
        if (!userRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                    new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot active or delete", ""));
        }
        if (food.isPresent()) {
            food.get().setStatus(status);
            food.get().setUpdatedBy(updateBy);
            return ResponseEntity.ok(
                    new ResponseData(HttpStatus.OK.value(), "Update successfully", FoodDTO.foodDTO(foodRepository.save(food.get()))));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find food with id " + id, ""));
    }

    @Override
    public ResponseEntity<ResponseData> updateListStatus(Integer status, List<Integer> idFood, Integer updateBy) {
        List<Food> foods = foodRepository.findAllById(idFood);
        UserRoleKey userRoleKey = new UserRoleKey(updateBy, 1);
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
        if (!userRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                    new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot active or delete", ""));
        }
        for (Food f : foods) {
            f.setStatus(status);
            f.setUpdatedBy(updateBy);
        }
        List<FoodDTO> foodDTOS = new ArrayList<>();
        List<Food> foodsUpdate = foodRepository.saveAll(foods);
        for (Food f : foodsUpdate) {
            foodDTOS.add(FoodDTO.foodDTO(f));
        }
        ClientSdi clientSdi = new ClientSdi();
        clientSdi.setUserId(updateBy);
        List<DataMailModel> dataMailModels = new ArrayList<>();
        for (FoodDTO item : foodDTOS) {
            dataMailModels.add(new DataMailModel(item.getName(), item.getDescription(), END_POINT_DETAIL_FOOD + item.getId(), END_POINT_CLOUDINARY + item.getAvatar()));
        }
        clientSdi.setUserId(updateBy);
        clientSdi.setDataMailModels(dataMailModels);
        clientService.createMail(clientSdi);
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully", foodDTOS));
    }


    @Override
    public ResponseEntity<ResponseData> findById(int id) {
        Optional<Food> food = foodRepository.findById(id);
        if (food.isPresent()) {
            User user = userRepository.findById(food.get().getCreatedBy()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find user with id " + food.get().getCreatedBy(), ""));
            }
            FoodDTO foodDTO = FoodDTO.foodDTO(food.get(), user.getName(), user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseData(HttpStatus.OK.value(), "Successful", foodDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find food with id " + id, ""));
        }
    }

    @Override
    public ResponseEntity<ResponseData> findAll(String name,
                                                Integer categoryId,
                                                Integer createdBy,
                                                Integer updatedBy,
                                                String expirationDate,
                                                String startCreated,
                                                String endCreated,
                                                String startUpdated,
                                                String endUpdated,
                                                int status, int page,
                                                String sortBy,
                                                int limit,
                                                String order) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (order.equals("asc")) {
            direction = Sort.Direction.ASC;
        }
        long endDate = -1L;
        if (!expirationDate.equals("")) {
            endDate = Utilities.convertStringToLong(expirationDate);
        }
        long startCreatedL = -1L;
        if (!startCreated.equals("")) {
            startCreatedL = Utilities.convertStringToLong(startCreated);
        }
        long endCreatedL = -1L;
        if (!endCreated.equals("")) {
            endCreatedL = Utilities.convertStringToLong(endCreated);
        }
        long startUpdatedL = -1L;
        if (!startUpdated.equals("")) {
            startUpdatedL = Utilities.convertStringToLong(startUpdated);
        }
        long endUpdatedL = -1L;
        if (!endUpdated.equals("")) {
            endUpdatedL = Utilities.convertStringToLong(endUpdated);
        }
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(direction, sortBy));
        if (limit > 0) {
            pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
        }
        Page<Food> foods = foodRepository.findAll(name,
                categoryId,
                createdBy,
                updatedBy,
                Calendar.getInstance().getTimeInMillis(),
                endDate,
                startCreatedL, endCreatedL, startUpdatedL, endUpdatedL,
                status,
                pageable);
        Page<FoodDTO> dtoPage = foods.map(new Function<Food, FoodDTO>() {
            @Override
            public FoodDTO apply(Food food) {
                return FoodDTO.foodDTO(food);
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successful", dtoPage));
    }

    @Override
    public ResponseEntity<ResponseData> getNearestLocation(double positionLongitude,
                                                           double positionLatitude,
                                                           double distance) {
//        Sort.Direction direction = Sort.Direction.DESC;
//        if (order.equals("asc")) {
//            direction = Sort.Direction.ASC;
//        }
//
//        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(direction, sortBy));
//        if (limit > 0) {
//            pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
//        }

        List<FoodPro> data = foodRepository.getNearestLocation(
                positionLongitude,
                positionLatitude,
                distance);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successful", data));
    }
}
