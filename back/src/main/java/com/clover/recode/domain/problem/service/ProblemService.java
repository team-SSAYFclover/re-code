package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.dto.ProblemCodeDto;

import java.util.List;
public interface ProblemService {
    void saveProblemAndCode(ProblemCodeDto dto);
}