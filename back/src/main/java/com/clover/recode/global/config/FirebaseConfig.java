package com.clover.recode.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
@Slf4j
public class FirebaseConfig {

    @Value("${firebase.config-path}")
    private String FIREBASE_CONFIG_PATH;
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
//    @Bean
//    public FirebaseApp initializeFirebaseApp() throws IOException {
//        GoogleCredentials credentials = GoogleCredentials.fromStream(
//                new ClassPathResource("path/to/your/firebase-service-account.json").getInputStream()
//        );
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .build();
//        return FirebaseApp.initializeApp(options);
//    }
}
