package com.example.hfb.service.serviceimpl;

import com.example.hfb.entity.Category;
import com.example.hfb.entity.Feedback;
import com.example.hfb.entity.Food;
import com.example.hfb.entity.User;
import com.example.hfb.model.FeedbackModel;
import com.example.hfb.model.ResponseData;
import com.example.hfb.model.dto.FeedbackDTO;
import com.example.hfb.model.dto.FoodDTO;
import com.example.hfb.repository.FeedbackRepository;
import com.example.hfb.repository.UserRepository;
import com.example.hfb.service.FeedbackService;
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

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<ResponseData> save(FeedbackModel model) {
        User user = userRepository.findById(model.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find user with id " + model.getUserId(), ""));
        }
        Feedback feedback = new Feedback(
                model.getImage(),
                model.getContent(),
                model.getRate(),
                model.getType(),
                model.getCreatedBy(),
                user,
                model.getUserId(),
                model.getRequestId()
        );
        User u = userRepository.findById(model.getUserId()).orElse(null);
        User sent = userRepository.findById(model.getCreatedBy()).orElse(null);
        FeedbackDTO feedbackDTO = FeedbackDTO.feedbackDTO(feedbackRepository.save(feedback), u, sent.getName());
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Insert successfully", feedbackDTO));
    }

    @Override
    public ResponseEntity<ResponseData> update(FeedbackModel model, Integer id) {
        List<String> errors = new ArrayList<>();
        if (model.getContent() == null) {
            errors.add("Content is empty");
        }
        if (model.getType() == null) {
            errors.add("Type is empty");
        }
        if (model.getUpdatedBy() == null) {
            errors.add("UpdatedBy is empty");
        }
        if (model.getStatus() == null) {
            errors.add("Status is empty");
        }
        if (model.getUserId() == null) {
            errors.add("UserId is empty");
        }
        if (model.getRequestId() == null) {
            errors.add("RequestId is empty");
        }
        if (errors.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseData(HttpStatus.NOT_IMPLEMENTED.value(), HttpStatus.NOT_IMPLEMENTED.toString(), errors));
        }
        User user = userRepository.findById(model.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find user with id " + model.getUserId(), ""));
        }

        Feedback feedbackUpdate = feedbackRepository.findById(id).map(feedback -> {
            feedback.setImage(model.getImage());
            feedback.setContent(model.getContent());
            feedback.setRate(model.getRate());
            feedback.setType(model.getType());
            feedback.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            feedback.setStatus(model.getStatus());
            feedback.setUserId(model.getUserId());
            feedback.setRequestId(model.getRequestId());
            feedback.setUpdatedBy(model.getUpdatedBy());
            feedback.setUser(user);
            return feedbackRepository.save(feedback);
        }).orElseGet(() -> null);
        if (feedbackUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find feedback with id " + id, ""));
        }
        User u = userRepository.findById(feedbackUpdate.getUserId()).orElse(null);
        User sent = userRepository.findById(feedbackUpdate.getCreatedBy()).orElse(null);
        FeedbackDTO feedbackDTO = FeedbackDTO.feedbackDTO(feedbackUpdate, u, sent.getName());
        return ResponseEntity.ok(
                new ResponseData(HttpStatus.OK.value(), "Update successfully", feedbackDTO));
    }

    @Override
    public ResponseEntity<ResponseData> findById(Integer id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            User u = userRepository.findById(feedback.get().getUserId()).orElse(null);
            User sent = userRepository.findById(feedback.get().getCreatedBy()).orElse(null);
            FeedbackDTO feedbackDTO = FeedbackDTO.feedbackDTO(feedback.get(), u, sent.getName());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseData(HttpStatus.OK.value(), "Successfully", feedbackDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseData(HttpStatus.NOT_FOUND.value(), "Cannot find feedback with id " + id, ""));
        }
    }

    @Override
    public ResponseEntity<ResponseData> findAll(Integer type, Integer status, Integer createdBy, Integer userId, Integer requestId, Integer startRate, Integer endRate, int page, String sortBy, int limit, String order) {
        Sort.Direction direction = Sort.Direction.DESC;
        if (order.equals("asc")){
            direction = Sort.Direction.ASC;
        }
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(direction, sortBy));
        if (limit > 0) {
            pageable = PageRequest.of(page, limit, Sort.by(direction, sortBy));
        }

        Page<Feedback> feedbacks = feedbackRepository.findAll(type, status, createdBy, userId, requestId, startRate, endRate, pageable);
        Page<FeedbackDTO> dtoPage = feedbacks.map(new Function<Feedback, FeedbackDTO>() {
            @Override
            public FeedbackDTO apply(Feedback feedback) {
                User u = userRepository.findById(feedback.getUserId()).orElse(null);
                User sent = userRepository.findById(feedback.getCreatedBy()).orElse(null);
                FeedbackDTO dto = FeedbackDTO.feedbackDTO(feedback, u, sent.getName());
                return dto;
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseData(HttpStatus.OK.value(), "Successfully", dtoPage));
    }

}
