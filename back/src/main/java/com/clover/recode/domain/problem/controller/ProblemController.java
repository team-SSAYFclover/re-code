package com.clover.recode.domain.problem.controller;


import static com.clover.recode.global.result.ResultCode.REGIST_CODE_SUCCESS;

import com.clover.recode.domain.problem.dto.ProblemCodeReq;
import com.clover.recode.domain.problem.service.ProblemService;
import com.clover.recode.global.result.ResultCode;
import com.clover.recode.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor //Lombok을 사용하여 생성자 자동 생성
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping()
    public ResponseEntity<ResultResponse> saveProblemAndCode(@RequestBody ProblemCodeReq problemCodeReq) {
        log.info("ProblemCode : {}", problemCodeReq);
        problemService.saveProblemAndCode(problemCodeReq);
        return ResponseEntity.ok(ResultResponse.of(REGIST_CODE_SUCCESS));
    }
}
