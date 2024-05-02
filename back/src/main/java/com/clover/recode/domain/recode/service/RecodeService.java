package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.dto.ProblemCodeDto;
import com.clover.recode.domain.recode.dto.RecodeRes;

public interface RecodeService {

    void saveRecode(ProblemCodeDto problemDto);
    RecodeRes getRecode(Long codeId);
    void addRecodeCount(int codeId);

}
