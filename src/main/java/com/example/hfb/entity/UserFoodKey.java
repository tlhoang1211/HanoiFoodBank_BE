package com.example.hfb.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserFoodKey implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "food_id")
    private int foodId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFoodKey that = (UserFoodKey) o;
        return userId == that.userId && foodId == that.foodId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, foodId);
    }


}
