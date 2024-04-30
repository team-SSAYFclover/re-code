package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.dto.ProblemCodeDto;
import com.clover.recode.domain.recode.dto.RecodeDto;

public interface RecodeService {

    void saveRecode(ProblemCodeDto problemDto);
    RecodeDto getRecode(int codeId);
    void addRecodeCount(int codeId);

}
