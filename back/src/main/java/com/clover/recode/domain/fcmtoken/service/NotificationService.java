package com.clover.recode.domain.fcmtoken.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    public void sendScheduledNotification();

}