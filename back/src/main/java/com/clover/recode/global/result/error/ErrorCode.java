package com.clover.recode.global.result.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Global
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류입니다."),
    METHOD_NOT_ALLOWED(405, "허용되지 않은 HTTP method입니다."),
    INPUT_VALUE_INVALID(400, "유효하지 않은 입력입니다."),
    INPUT_TYPE_INVALID(400, "입력 타입이 유효하지 않습니다."),
    HTTP_MESSAGE_NOT_READABLE(400, "request message body가 없거나, 값 타입이 올바르지 않습니다."),
    HTTP_HEADER_INVALID(400, "request header가 유효하지 않습니다."),
    ENTITY_NOT_FOUNT(500, "존재하지 않는 Entity입니다."),
    FORBIDDEN_ERROR(403, "작업을 수행하기 위한 권한이 없습니다."),
    IS_NOT_IMAGE(400, "이미지가 아닙니다."),

    //User
    USER_NOT_EXISTS(404, "존재하지 않는 회원입니다."),
    REFRESH_TOKEN_NOT_AVAILABLE(401, "리프레시 토큰 형식이 유효하지 않습니다."),
    REFRESH_TOKEN_INVALID(401, "유효한 리프레시 토큰이 아닙니다."),
    INVALID_USER_ID(400, "유효한 ID 값이 아닙니다."),

    //Problem
    PROBLEM_NOT_EXISTS(404, "존제하지 않는 문제입니다."),
    CODE_ALREADY_EXISTS(409, "이미 복습 리스트에 존재하는 코드입니다."),

    //Code
    Already_REGIST_CODE(409, "이미 등록한 코드입니다."),
    CODE_NOT_EXISTS(404, "해당 코드를 찾을 수 없습니다."),

    //Recode
    RECODE_NOT_ALLOWED(401, "허가되지 않은 사용자 입니다."),
    RECODE_NOT_EXISTS(404, "레코드를 찾을 수 없습니다."),

    //통계
    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다.");



    private final int status;
    private final String message;

}