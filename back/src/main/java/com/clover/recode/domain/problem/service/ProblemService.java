package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.dto.ProblemDto;

import java.util.List;

public interface ProblemService {
    ProblemDto createProblem(ProblemDto problemDto);
    ProblemDto updateProblem(Long id, ProblemDto problemDto);
    void deleteProblem(Long id);
    ProblemDto getProblemById(Long id);
    List<ProblemDto> getAllProblems();
}
