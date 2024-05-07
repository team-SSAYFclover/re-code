package com.clover.recode.domain.fcmtoken.service;

import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.SettingRepository;
import com.clover.recode.domain.user.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    //private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final SettingRepository settingRepository;


    @Scheduled(cron = "0 */30 * * * ?") // 매 30분마다 실행
    public void sendScheduledNotification() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.SECOND, 0);  // 초를 0으로 설정
        java.util.Date nowDate = now.getTime();
        Time currentTime = new Time(nowDate.getTime());

//        List<Setting> settings = settingRepository.findSettingsByNotificationTime(currentTime);
//        for (Setting setting : settings) {
//            User user = setting.getUser();
//            if (user != null && user.getFcmToken() != null) {
//                try {
//                        sendNotification(user.getFcmToken(),
//                        "RE:CODE",
//                        "오늘의 복습 문제가 기다리고 있어요!");
//
//                } catch (Exception e) {
//                    log.error("Failed to send notification to " + user.getId() + ": " + e.getMessage());
//                }
//            }
//        }
    }

    // 푸시 알림 보내기
    public void sendNotification(String token, String title, String body) throws FirebaseMessagingException {

        // Notification의 내용을 설정
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        // Message 객체를 통해 푸시 토큰과 Notification 객체를 설정
        Message message = Message.builder()
                .setToken(token)  // 토큰을 설정
                .setNotification(notification)
                .build();

        // 메시지 전송
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }


}