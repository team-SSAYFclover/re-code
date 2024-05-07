package com.clover.recode.domain.notification.controller;

import com.clover.recode.domain.notification.service.NotificationService;
import com.clover.recode.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.clover.recode.global.result.ResultCode.REGIST_CODE_SUCCESS;
import static com.clover.recode.global.result.ResultCode.REGIST_FCM_TOKEN_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;


    // FCM 토큰을 DB에 저장하는 로직
    @PostMapping("/newFcmToken")
    public ResponseEntity<ResultResponse> saveFcmToken(@RequestBody Authentication authentication, String token) {
        notificationService.saveFcmToken(authentication, token);
        return ResponseEntity.ok(ResultResponse.of(REGIST_FCM_TOKEN_SUCCESS));
    }
}