package com.clover.recode.global.result.error.exception;

import com.clover.recode.global.result.error.ErrorCode;
import com.clover.recode.global.result.error.ErrorResponse;
import com.clover.recode.global.result.error.ErrorResponse.FieldError;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private List<FieldError> errors = new ArrayList<>();

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, List<ErrorResponse.FieldError> errors) {
        super(errorCode.getMessage());
        this.errors = errors;
        this.errorCode = errorCode;
    }
}