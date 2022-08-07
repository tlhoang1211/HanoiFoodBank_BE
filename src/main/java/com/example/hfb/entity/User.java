package com.example.hfb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private int pointEvaluation;
    private double positionLatitude;
    private double positionLongitude;
    private long createdAt;
    private long updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private int status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<UserRole> userRoles = new HashSet<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Set<Request> requests = new HashSet<>();


    public void setRequests(Set<Request> requests) {
        this.requests = requests;
        for(Request r : requests) {
            r.setUser(this);
        }
    }

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks = new HashSet<>();
    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
        for(Feedback f : feedbacks) {
            f.setUser(this);
        }
    }

    public User(String name, String username, String password, String phone, String address, String avatar, double positionLatitude, double positionLongitude) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.pointEvaluation = 0;
        this.positionLatitude = positionLongitude;
        this.positionLongitude = positionLatitude;
        this.createdBy = this.id;
        this.updatedBy = this.id;
        this.email = username;
        this.status = 1;
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
    }

    public User(Integer id, String name, String username, String password, String email, String phone, String address, String avatar, int pointEvaluation, long createdAt, long updatedAt, Integer createdBy, Integer updatedBy, int status, double positionLongitude, double positionLatitude) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
        this.pointEvaluation = pointEvaluation;
        this.positionLongitude = positionLongitude;
        this.positionLatitude = positionLatitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.status = status;
    }


}
