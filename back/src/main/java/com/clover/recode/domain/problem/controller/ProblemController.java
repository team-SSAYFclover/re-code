package com.clover.recode.domain.problem.controller;


import com.clover.recode.domain.problem.dto.CodePatchReq;
import com.clover.recode.domain.problem.dto.ProblemCodeRegistReq;
import com.clover.recode.domain.problem.dto.ProblemDetailRes;
import com.clover.recode.domain.problem.service.ProblemService;
import com.clover.recode.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.clover.recode.global.result.ResultCode.*;

@Slf4j
@RestController
@RequiredArgsConstructor //Lombok을 사용하여 생성자 자동 생성
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping()
    public ResponseEntity<ResultResponse> saveProblemAndCode(@RequestBody ProblemCodeRegistReq problemCodeRegistReq) {
        log.info("ProblemCode : {}", problemCodeRegistReq);
        problemService.saveProblemAndCode(problemCodeRegistReq);
        return ResponseEntity.ok(ResultResponse.of(REGIST_CODE_SUCCESS));
    }


    //사용자 문제 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "사용자별 문제 조회")
    public ResponseEntity<ResultResponse> userProblemList(
            Authentication authentication,
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) Integer start,
            @RequestParam(required = false) Integer end,
            @RequestParam(required = false) List<String> tag,
            @RequestParam(required = false) String keyword
    ){

        return ResponseEntity.ok(ResultResponse.of(USER_PROBLEM_SUCCESS,
                problemService.findUserProblems(authentication,pageable, start, end, tag, keyword)));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{problemNo}")
    @Operation(summary = "문제 상세 조회")
    public ResponseEntity<ResultResponse> getProblemDetails(Authentication authentication, @PathVariable Integer problemNo) {
        return ResponseEntity.ok(ResultResponse.of(PROBLEM_DETAIL_SUCCESS,
                problemService.getProblemDetails(authentication, problemNo)));
    }

    @PatchMapping("/code/{codeId}")
    public ResponseEntity<ResultResponse> patchCode(@PathVariable Long codeId, @RequestBody
    CodePatchReq codePatchReq, Authentication authentication) {
        problemService.patchCode(codeId, codePatchReq, authentication);
        return ResponseEntity.ok(ResultResponse.of(PATCH_CODE_SUCCESS));
    }

    @DeleteMapping("/code/{codeId}")
    public ResponseEntity<ResultResponse> deleteCode(@PathVariable Long codeId, Authentication authentication) {
        problemService.deleteCode(codeId, authentication);
        return ResponseEntity.ok(ResultResponse.of(DELETE_CODE_SUCCESS));
    }

}
