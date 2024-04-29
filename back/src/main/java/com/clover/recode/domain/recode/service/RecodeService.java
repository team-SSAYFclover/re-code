package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.recode.dto.RecodeDto;
import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;

public interface RecodeService {

    void saveRecode(ProblemDto problemDto);
    RecodeDto getRecode(int codeNo);
    void addRecodeCount(int codeNo);

}
