package com.clover.recode.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    //Statistics
    GET_Statistics_SUCCESS(200, "메인화면 통계를 가져오는 것에 성공했습니다."),
    //Recode
    GET_Recode_SUCCESS(200, "레코드를 가져오는데 성공했습니다."),
    //Recode
    PUT_Recode_SUCCESS(201, "레코드 푼 횟수를 추가하는데 성공했습니다.");

    private final int status;
    private final String message;

}