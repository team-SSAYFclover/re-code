package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.repository.ProblemRepository;
import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.entity.Problem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemServiceImpl implements ProblemService {


    private final ProblemRepository problemRepository;

    @Override
    public ProblemDto createProblem(ProblemDto problemDto) {
        Problem problem = mapToEntity(problemDto);
        Problem newProblem = problemRepository.save(problem);
        return mapToDto(newProblem);
    }

    @Override
    public ProblemDto updateProblem(Long id, ProblemDto problemDto) {
        Problem problem = problemRepository.findById(id).orElseThrow(() -> new RuntimeException("Problem not found"));
        problem.setProblemNo(problemDto.getProblemNo());
        problem.setTitle(problemDto.getTitle());
        problem.setLevel(problemDto.getLevel());
        problem.setReviewCount(problemDto.getReviewCount());
        problem.setTags(problemDto.getTags());
        Problem updatedProblem = problemRepository.save(problem);
        return mapToDto(updatedProblem);
    }

    @Override
    public void deleteProblem(Long id) {
        problemRepository.deleteById(id);
    }

    @Override
    public ProblemDto getProblemById(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(() -> new RuntimeException("Problem not found"));
        return mapToDto(problem);
    }

    @Override
    public List<ProblemDto> getAllProblems() {
        return problemRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private Problem mapToEntity(ProblemDto dto) {
        Problem problem = new Problem();
        problem.setId(dto.getId());
        problem.setProblemNo(dto.getProblemNo());
        problem.setTitle(dto.getTitle());
        problem.setLevel(dto.getLevel());
        problem.setReviewCount(dto.getReviewCount());
        problem.setTags(dto.getTags());
        return problem;
    }

    private ProblemDto mapToDto(Problem problem) {
        return new ProblemDto(problem.getId(), problem.getProblemNo(), problem.getTitle(), problem.getLevel(), problem.getReviewCount(), problem.getTags());
    }
}
