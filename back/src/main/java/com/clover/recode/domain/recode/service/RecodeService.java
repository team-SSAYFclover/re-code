package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.recode.dto.RecodeDto;

public interface RecodeService {

    void saveRecode(ProblemDto problemDto);
    RecodeDto getRecode(int codeId);
    void addRecodeCount(int codeId);

}
