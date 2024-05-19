package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.recode.dto.RecodeRes;
import org.springframework.security.core.Authentication;

public interface RecodeService {

    void saveRecode(Code code);
    RecodeRes getRecode(Authentication authentication, Long codeId);
    void recodeSubmit(Long codeId);

}
