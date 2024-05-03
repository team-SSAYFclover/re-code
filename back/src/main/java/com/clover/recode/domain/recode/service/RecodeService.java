package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.recode.dto.RecodeRes;

public interface RecodeService {

    void saveRecode(Code code);
    RecodeRes getRecode(Long codeId);
    void recodeSubmit(Long codeId);

}
