package com.clover.recode.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    //User
    GET_USER_INFO_SUCCESS(200, "회원 정보 조회에 성공하였습니다."),
    GET_USER_NUMBER_SUCCESS(200, "회원 고유 번호 조회에 성공하였습니다."),
    TOKEN_REISSUE_SUCCESS(201, "액세스 토큰 재발급에 성공하였습니다."),
    PATCH_USER_SETTING_SUCCESS(201, "회원 설정 변경에 성공하였습니다."),
    POST_TOKEN_SUCCESS(201, "FCM 토큰 등록에 성공하였습니다"),

    //Problem
    REGIST_CODE_SUCCESS(201, "코드 등록에 성공하였습니다."),

    //Statistics
    GET_Statistics_SUCCESS(200, "메인화면 통계를 가져오는 것에 성공했습니다."),
    GET_ReviewCnt_SUCCESS(200, "오늘의 복습 갯수 조회에 성공하였습니다"),
    GET_Reviews_SUCCESS(200, "오늘의 복습문제 조회에 성공하였습니다"),


    //Recode
    GET_Recode_SUCCESS(200, "레코드를 가져오는데 성공했습니다."),
    PUT_Recode_SUCCESS(201, "레코드 푼 횟수를 추가하는데 성공했습니다."),


    //FCM token
    REGIST_FCM_TOKEN_SUCCESS(201, "FCM 토큰 등록에 성공했습니다."),

    //사용자 문제 조회
    USER_PROBLEM_SUCCESS(200, "사용자별 문제 조회에 성공했습니다.");



    private final int status;
    private final String message;

}