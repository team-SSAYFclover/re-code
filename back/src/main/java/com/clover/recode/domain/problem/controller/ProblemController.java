package com.clover.recode.domain.problem.controller;


import com.clover.recode.domain.problem.dto.ProblemCodeDto;
import com.clover.recode.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor //Lombok을 사용하여 생성자 자동 생성
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping("/problems")
    public ResponseEntity<?> saveProblemAndCode(@RequestBody ProblemCodeDto dto) {
        problemService.saveProblemAndCode(dto);
        return ResponseEntity.ok().build();
    }
}
