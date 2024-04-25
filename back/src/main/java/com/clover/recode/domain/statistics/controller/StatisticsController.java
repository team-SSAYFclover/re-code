package com.clover.recode.domain.statistics.controller;

import com.clover.recode.domain.statistics.service.StatisticsService;
import com.clover.recode.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.clover.recode.global.result.ResultCode.GET_Statistics_SUCCESS;

@Slf4j
@RestController
@Tag(name = "통계 API")
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "메인화면의 모든 통계들 정보 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public ResponseEntity<ResultResponse> getStatisticsList(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(ResultResponse.of(GET_Statistics_SUCCESS, statisticsService.getStatisticsList(userId)));
    }


}