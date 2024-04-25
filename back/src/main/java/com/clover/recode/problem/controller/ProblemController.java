package com.clover.recode.problem.controller;

import com.clover.recode.problem.dto.ProblemDto;
import com.clover.recode.problem.service.ProblemService;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

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

    @GetMapping
    public ResponseEntity<List<ProblemDto>> getAllProblems() {
        List<ProblemDto> problems = problemService.getAllProblems();
        return ResponseEntity.ok(problems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProblemDto> updateProblem(@PathVariable Long id, @RequestBody ProblemDto problemDto) {
        ProblemDto updatedProblem = problemService.updateProblem(id, problemDto);
        return ResponseEntity.ok(updatedProblem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
        return ResponseEntity.ok().build();
    }
}
