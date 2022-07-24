package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.*;
import com.example.hfb.model.RequestModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.RequestDTO;
import com.example.hfb.model.dto.RequestDetail;
import com.example.hfb.repository.FoodRepository;
import com.example.hfb.repository.RequestRepository;
import com.example.hfb.repository.UserRepository;
import com.example.hfb.repository.UserRoleRepository;
import com.example.hfb.service.RequestService;
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

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public ResponseEntity<ResponseData> saveRequest(RequestModel model) {
        User user = userRepository.findById(model.getUserId()).orElse(null);
        Food food = foodRepository.findById(model.getFoodId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find user in system", ""));
        } else if (food == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find food in system", ""));
        }
        UserFoodKey userFoodKey = new UserFoodKey(user.getId(), food.getId());
        Optional<Request> req = requestRepository.findById(userFoodKey);
        User supplier = userRepository.findById(food.getCreatedBy()).orElse(null);
        if (user.getId() == supplier.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                    new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, do not self-edit", ""));
        }

        if (req.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED.value()).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), "Already requested", ""));
        }

        Integer requestCount = requestRepository.requestCount(user.getId());
        if (requestCount == 3) {
            return ResponseEntity.status(HttpStatus.OK.value()).body(
                    new ResponseData(HttpStatus.OK.value(), "Warning", "Warning! You have reach the limitation on requesting food for today."));
        }

        Request request = new Request(new UserFoodKey(user.getId(), food.getId()), user, food, supplier.getId(), supplier.getName(), model.getMessage());
        requestRepository.save(request);

        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseData(HttpStatus.OK.value(), "Success", RequestDTO.requestDTO(request)));

    }

    @Override
    public ResponseEntity<ResponseData> update(RequestModel model, int userId, int foodId) {
        User user = userRepository.findById(userId).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find user in system", ""));
        } else if (food == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find food in system", ""));
        }

        User supplier = userRepository.findById(food.getCreatedBy()).orElse(null);
        UserFoodKey userFoodKey = new UserFoodKey(userId, foodId);
        Optional<Request> request = requestRepository.findById(userFoodKey);
        if (request.isPresent()) {
            UserRoleKey userRoleKey = new UserRoleKey(model.getUpdatedBy(), 1);
            Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
            if (!userRole.isPresent()) {
                int createBy = request.get().getCreatedBy();
                if (model.getStatus() == 2 && (model.getUpdatedBy() != food.getCreatedBy())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                            new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot confirm", ""));
                } else if (model.getStatus() == 3 && model.getUpdatedBy() != createBy) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                            new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot done", ""));
                }
            }
        }
        Request reqUpdate = request.map(req -> {
            req.setMessage(model.getMessage());
            req.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            req.setUpdatedBy(model.getUpdatedBy());
            req.setStatus(model.getStatus());
            return requestRepository.save(req);
        }).orElseGet(() -> requestRepository.save(new Request(new UserFoodKey(user.getId(), food.getId()), user, food, supplier.getId(), supplier.getName(), model.getMessage())));

        RequestDTO requestDTO = RequestDTO.requestDTO(reqUpdate);
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully", requestDTO));

    }

    @Override
    public ResponseEntity<ResponseData> updateStatus(RequestModel model, Integer userId, Integer foodId) {
        User user = userRepository.findById(userId).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find user in system", ""));
        } else if (food == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find food in system", ""));
        }
        UserFoodKey userFoodKey = new UserFoodKey(userId, foodId);
        Optional<Request> request = requestRepository.findById(userFoodKey);
        UserRoleKey userRoleKey = new UserRoleKey(model.getUpdatedBy(), 1);
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
        if (request.isPresent()) {
            if (!userRole.isPresent()) {
                int createBy = request.get().getCreatedBy();
                if (model.getStatus() == 2 && (model.getUpdatedBy() != food.getCreatedBy())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                            new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot confirm", ""));
                } else if (model.getStatus() == 3 && (model.getUpdatedBy() != createBy)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                            new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot done", ""));
                }
            }

            request.get().setStatus(model.getStatus());
            request.get().setUpdatedBy(model.getUpdatedBy());
            if (model.getStatus() == 3) {
                food.setStatus(0);
                foodRepository.save(food);
            }

            return ResponseEntity.ok(
                    new ResponseData(HttpStatus.OK.value(), "Update successfully", RequestDTO.requestDTO(requestRepository.save(request.get()))));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot found request in database", ""));
    }

    @Override
    public ResponseEntity<ResponseData> updateListStatus(RequestModel model) {
        List<UserFoodKey> userFoodKeys = model.getUserFoodKeys();
        List<Request> requests = requestRepository.findAllById(userFoodKeys);
        UserRoleKey userRoleKey = new UserRoleKey(model.getUpdatedBy(), 1);
        Optional<UserRole> userRole = userRoleRepository.findById(userRoleKey);
        for (Request r : requests) {
            if (!userRole.isPresent()) {
                int createBy = r.getCreatedBy();
                if (model.getStatus() == 2 && (model.getUpdatedBy() != r.getFood().getCreatedBy())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                            new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot confirm", ""));
                } else if (model.getStatus() == 3 && (model.getUpdatedBy() != createBy)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(
                            new ResponseData(HttpStatus.FORBIDDEN.value(), "Access denied, you cannot done", ""));
                }
            }

            r.setStatus(model.getStatus());
            r.setUpdatedBy(model.getUpdatedBy());
        }
        List<RequestDTO> requestDTOS = new ArrayList<>();
        List<Request> requestsUpdate = requestRepository.saveAll(requests);
        for (Request r : requestsUpdate) {
            requestDTOS.add(RequestDTO.requestDTO(r));
        }
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully", requestDTOS));
    }

    @Override
    public ResponseEntity<ResponseData> getRequest(int userId, int foodId) {
        User user = userRepository.findById(userId).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find user in system", ""));
        } else if (food == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Can't find food in system", ""));
        }
        UserFoodKey userFoodKey = new UserFoodKey(user.getId(), food.getId());
        Optional<Request> req = requestRepository.findById(userFoodKey);

        Integer requestCount = requestRepository.requestCount(userId);

        if (req.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK.value()).body(
                    new ResponseData(HttpStatus.OK.value(), "Success", RequestDTO.requestDTO(req.get()), requestCount));
        } else {
            return ResponseEntity.status(HttpStatus.OK.value()).body(
                    new ResponseData(HttpStatus.OK.value(), "The user's request for this item was not found on the system", ""));
        }
    }

    @Override
    public ResponseEntity<ResponseData> findAll(int userId,
                                                int foodId,
                                                String startCreated,
                                                String endCreated,
                                                String startUpdated,
                                                String endUpdated,
                                                int status,
                                                int page,
                                                String sortBy,
                                                int limit,
                                                String order) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (order.equals("asc")) {
            direction = Sort.Direction.ASC;
        }
        Long startCreatedL = -1L;
        if (!startCreated.equals("")) {
            startCreatedL = Utilities.convertStringToLong(startCreated);
        }
        Long endCreatedL = -1L;
        if (!endCreated.equals("")) {
            endCreatedL = Utilities.convertStringToLong(endCreated);
        }
        Long startUpdatedL = -1L;
        if (!startUpdated.equals("")) {
            startUpdatedL = Utilities.convertStringToLong(startUpdated);
        }
        Long endUpdatedL = -1L;
        if (!endUpdated.equals("")) {
            endUpdatedL = Utilities.convertStringToLong(endUpdated);
        }
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(direction, sortBy));
        if (limit > 0) {
            pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
        }
        Page<RequestDetail> requests = requestRepository.findAllInfo(userId, foodId, startCreatedL, endCreatedL, startUpdatedL, endUpdatedL, status, pageable);

        Integer requestCount = requestRepository.requestCount(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", requests, requestCount));
    }
}
