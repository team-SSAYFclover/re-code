package com.clover.recode.domain.fcmtoken.service;

import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.SettingRepository;
import com.clover.recode.domain.user.repository.UserRepository;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final UserRepository userRepository;
    private final SettingRepository settingRepository;


    @Scheduled(cron = "0 */15 * * * ?") // 매 30분마다 실행
    public void sendScheduledNotification() {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Asia/seoul"));
        now.set(Calendar.SECOND, 0);  // 초를 0으로 설정
        now.set(Calendar.MILLISECOND, 0);
        java.util.Date nowDate = now.getTime();
        Time currentTime = new Time(nowDate.getTime());

        List<String> tokens = userRepository.findByFcmTokens(currentTime);

        if(!tokens.isEmpty()) {
            try {
                sendNotification(tokens,
                    "RE:CODE",
                    "오늘의 복습 문제가 기다리고 있어요!");
            } catch (Exception e) {
                log.error("Failed to send notification");
            }
        }

    }

    // 푸시 알림 보내기
    public void sendNotification(List<String> tokens, String title, String body) throws FirebaseMessagingException {

        // Message 객체를 통해 푸시 토큰과 Notification 객체를 설정
        MulticastMessage message = MulticastMessage.builder()
                .putData("title", title)
                .putData("body",body)
                .addAllTokens(tokens)  // 토큰을 설정
                .build();

        // 메시지 전송
        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
        System.out.println("messages were sent successfully");
    }

}