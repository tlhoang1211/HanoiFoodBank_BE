package com.example.hfb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    private int rate;
    private int type;
    private long createdAt;
    private long updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Column(insertable = false, updatable = false)
    private int userId;

    public Feedback(String image, String content, int rate, int type, Integer createdBy, User user, int userId) {
        this.image = image;
        this.content = content;
        this.rate = rate;
        this.type = type;
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.status = 1;
        this.user = user;
        this.userId = userId;
    }

    public Feedback(Integer id, String image, String content, int rate, int type, Integer createdBy, User user, int userId) {
        this.id = id;
        this.image = image;
        this.content = content;
        this.rate = rate;
        this.type = type;
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.status = 1;
        this.user = user;
        this.userId = userId;
    }
}
