package com.clover.recode.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    //User
    GET_USER_INFO_SUCCESS(200, "회원 정보 조회에 성공하였습니다."),
    TOKEN_REISSUE_SUCCESS(200, "액세스 토큰 재발급에 성공하였습니다."),


    //Statistics
    GET_Statistics_SUCCESS(200, "메인화면 통계를 가져오는 것에 성공했습니다.");

    private final int status;
    private final String message;

}