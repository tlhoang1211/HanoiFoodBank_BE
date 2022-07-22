package com.example.hfb.model.dto;

import com.example.hfb.entity.Feedback;
import com.example.hfb.entity.Food;
import com.example.hfb.entity.User;
import com.example.hfb.utilities.Utilities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackDTO {
    private Integer id;
    private String image;
    private String content;
    private int rate;
    private int type;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private String sentAvatar;
    private String sentName;
    private String foodName;
    private String foodCategory;
    private String name;
    private String username;
    private String avatar;
    private Integer updatedBy;
    private int userId;
    private int foodId;
    private int status;

    public static FeedbackDTO feedbackDTO(Feedback feedback){
        FeedbackDTO tmp = new FeedbackDTO();
        tmp.setId(feedback.getId());
        tmp.setImage(feedback.getImage());
        tmp.setContent(feedback.getContent());
        tmp.setRate(feedback.getRate());
        tmp.setType(feedback.getType());
        tmp.setCreatedAt(Utilities.convertLongToDate(feedback.getCreatedAt()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(feedback.getUpdatedAt()));
        tmp.setCreatedBy(feedback.getCreatedBy());
        tmp.setUpdatedBy(feedback.getUpdatedBy());
        tmp.setUserId(feedback.getUserId());
        tmp.setFoodId(feedback.getFoodId());
        tmp.setStatus(feedback.getStatus());
        return tmp;
    }

    public static FeedbackDTO feedbackDTO(Feedback feedback, User user, String sentName, String sentAvatar, Food food, String foodName, String foodCategory){
        FeedbackDTO tmp = new FeedbackDTO();
        tmp.setId(feedback.getId());
        tmp.setImage(feedback.getImage());
        tmp.setContent(feedback.getContent());
        tmp.setRate(feedback.getRate());
        tmp.setType(feedback.getType());
        tmp.setCreatedAt(Utilities.convertLongToDate(feedback.getCreatedAt()));
        tmp.setUpdatedAt(Utilities.convertLongToDate(feedback.getUpdatedAt()));
        tmp.setCreatedBy(feedback.getCreatedBy());
        tmp.setSentName(sentName);
        tmp.setSentAvatar(sentAvatar);
        tmp.setFoodName(foodName);
        tmp.setFoodCategory(foodCategory);
        tmp.setName(user.getName());
        tmp.setUsername(user.getUsername());
        tmp.setAvatar(user.getAvatar());
        tmp.setUpdatedBy(feedback.getUpdatedBy());
        tmp.setUserId(feedback.getUserId());
        tmp.setFoodId(feedback.getFoodId());
        tmp.setStatus(feedback.getStatus());
        return tmp;
    }
}
