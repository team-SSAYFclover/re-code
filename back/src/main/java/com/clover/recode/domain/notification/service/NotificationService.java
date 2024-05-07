package com.clover.recode.domain.notification.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    public void saveFcmToken(Authentication authentication, String token);

    public void sendNotification(String token, String title, String body) throws FirebaseMessagingException;
    public void sendScheduledNotification();

}