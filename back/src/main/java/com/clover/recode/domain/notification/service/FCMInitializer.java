package com.clover.recode.domain.notification.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class FCMInitializer {


    private static final String FIREBASE_CONFIG_PATH = "firebase/recode-43ead-firebase-adminsdk-vhyqn-37b7e61941.json";

    // json파일을 찾아서 맞는 정보인지 확인한 후 FirebaseApp.initializeApp()을 통해서 실행
    // 서버 실행 시, 단 한번 실행되어야 하므로 PostConstruct 어노테이션 필요
    @PostConstruct
    public void initialize() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream()); //resources 폴더 아래에 있는 괄호 안 경로를 찾음.
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(googleCredentials)
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            log.info(">>>>>>>>FCM error");
            log.error(">>>>>>FCM error message : " + e.getMessage());
        }
    }
}