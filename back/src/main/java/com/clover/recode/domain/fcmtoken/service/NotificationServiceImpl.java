package com.clover.recode.domain.fcmtoken.service;

import com.clover.recode.domain.fcmtoken.repository.FcmTokenRepository;
import com.clover.recode.domain.user.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final UserRepository userRepository;
    private final FcmTokenRepository fcmTokenRepository;


    @Scheduled(cron = "0 */1 * * * ?") // 매 30분마다 실행
    @Transactional
    public void sendScheduledNotification() {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Asia/seoul"));
        now.set(Calendar.SECOND, 0);  // 초를 0으로 설정
        now.set(Calendar.MILLISECOND, 0);
        java.util.Date nowDate = now.getTime();
        Time currentTime = new Time(nowDate.getTime());

        List<String> tokens = userRepository.findByFcmTokens(currentTime);

        for(String token : tokens) {
            if(!tokens.isEmpty()) {
                try {
                    sendNotification(token,
                            "RE:CODE",
                            "오늘의 복습 문제가 기다리고 있어요!");
                } catch (Exception e) {
                    fcmTokenRepository.deleteFcmTokenByToken(token);
                }
            }
        }

    }

    // 푸시 알림 보내기
    public void sendNotification(String token, String title, String body) throws FirebaseMessagingException {

        // Message 객체를 통해 푸시 토큰과 Notification 객체를 설정
        Message message = Message.builder()
                .putData("title", title)
                .putData("body",body)
                .setToken(token)  // 토큰을 설정
                .build();

        // 메시지 전송
        String response = FirebaseMessaging.getInstance().send(message);
    }

}