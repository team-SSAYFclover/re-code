package com.clover.recode.domain.problem.controller;

import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor //Lombok을 사용하여 생성자 자동 생성
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemService problemService;

    // 생성자를 통한 의존성 주입 -> @RequiredArgsConstructor
//    public ProblemController(ProblemService problemService) {
//        this.problemService = problemService;
//    }


    @PostMapping
    public ResponseEntity<ProblemDto> createProblem(@RequestBody ProblemDto problemDto) {
        ProblemDto createdProblem = problemService.createProblem(problemDto);
        return ResponseEntity.ok(createdProblem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDto> getProblemById(@PathVariable Long id) {
        ProblemDto problemDto = problemService.getProblemById(id);
        return ResponseEntity.ok(problemDto);
    }
}
