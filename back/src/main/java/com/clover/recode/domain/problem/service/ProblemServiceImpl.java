package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.repository.ProblemRepository;
import com.clover.recode.domain.problem.service.ProblemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 롬복을 사용하여 생성자 자동 생성
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public ProblemDto createProblem(ProblemDto problemDto) {
        Problem problem = modelMapper.map(problemDto, Problem.class);
        Problem savedProblem = problemRepository.save(problem);
        return modelMapper.map(savedProblem, ProblemDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public ProblemDto getProblemById(Long id) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Problem not found with ID: " + id));
        return modelMapper.map(problem, ProblemDto.class);
    }
}