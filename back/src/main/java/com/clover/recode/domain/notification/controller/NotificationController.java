package com.clover.recode.domain.notification.controller;

import com.clover.recode.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;


    // FCM 토큰을 DB에 저장하는 로직
    @PostMapping("/newFcmToken")
    public void saveFcmToken(@RequestBody Authentication authentication, String token) {
        notificationService.saveFcmToken(authentication, token);
    }
}