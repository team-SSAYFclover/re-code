package com.clover.recode.domain.statistics.controller;

import com.clover.recode.domain.statistics.service.StatisticsService;
import com.clover.recode.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.clover.recode.global.result.ResultCode.*;

@Slf4j
@RestController
@Tag(name = "통계 API")
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "메인화면의 모든 통계들 정보 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<ResultResponse> getStatisticsList(Authentication authentication) {
        return ResponseEntity.ok(ResultResponse.of(GET_Statistics_SUCCESS, statisticsService.getStatisticsList(authentication)));
    }

    @Operation(summary = "오늘의 복습문제 갯수 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/reviews/cnt")
    public ResponseEntity<ResultResponse> getReviewsCnt(@PathVariable Long userId) {
        Integer count = statisticsService.getReviewCnt(userId);
        if(count == null)
            count = 0;

        return ResponseEntity.ok(ResultResponse.of(GET_ReviewCnt_SUCCESS, count));
    }

    @Operation(summary = "오늘의 복습문제 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/today/reviews")
    public ResponseEntity<ResultResponse> getReviews(Authentication authentication) {
        return ResponseEntity.ok(ResultResponse.of(GET_Reviews_SUCCESS, statisticsService.getReviews(authentication)));
    }

    @Operation(summary = "랜덤문제 업데이트")
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/problem/random")
    public ResponseEntity<ResultResponse> updateRandom(Authentication authentication) {
        return ResponseEntity.ok(ResultResponse.of(GET_Reviews_SUCCESS, statisticsService.updateRandom(authentication)));
    }

    @Operation(summary = "보충문제 업데이트")
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/problem/supplement")
    public ResponseEntity<ResultResponse> updateSupplement(Authentication authentication) {
        return ResponseEntity.ok(ResultResponse.of(GET_Reviews_SUCCESS, statisticsService.updateSupplement(authentication)));
    }


}
