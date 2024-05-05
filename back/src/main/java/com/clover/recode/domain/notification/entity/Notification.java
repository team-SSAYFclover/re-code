//package com.clover.recode.domain.notification.entity;
//
//import com.clover.recode.domain.user.entity.User;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Notification {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "notification_id")
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    private String token;
//
//    @Builder
//    public Notification(String token) {
//        this.token = token;
//    }
//
//    public void confirmUser(User user) {
//        this.user = user;
//    }
//}