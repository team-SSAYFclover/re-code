package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.dto.ProblemCodeReq;

import java.util.List;
public interface ProblemService {
    void saveProblemAndCode(ProblemCodeReq problemCodeReq);

}