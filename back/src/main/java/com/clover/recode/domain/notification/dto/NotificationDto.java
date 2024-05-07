//package com.clover.recode.domain.notification.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//
///**
// * FCM 전송 Format DTO
// *
// * @author : lee
// * @fileName : FcmMessageDto
// * @since : 2/21/24
// */
//@Getter
//@Builder
//public class NotificationDto {
//    private boolean validateOnly;
//
//    @Builder
//    @AllArgsConstructor
//    @Getter
//    public static class Message {
//        private NotificationDto.Notification notification;
//        private String token;
//    }
//
//    @Builder
//    @AllArgsConstructor
//    @Getter
//    public static class Notification {
//        private String title;
//        private String body;
//        private String image;
//    }
//}