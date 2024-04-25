package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.dto.ProblemDto;

import java.util.List;

public interface ProblemService {
    ProblemDto createProblem(ProblemDto problemDto); // 문제 생성
    ProblemDto getProblemById(Long id); // 문제 ID로 문제 상세 조회
}
