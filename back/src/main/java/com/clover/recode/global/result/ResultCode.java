package com.clover.recode.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    //Statistics
    GET_Statistics_SUCCESS(200, "메인화면 통계를 가져오는 것에 성공했습니다.");

    private final int status;
    private final String message;

}