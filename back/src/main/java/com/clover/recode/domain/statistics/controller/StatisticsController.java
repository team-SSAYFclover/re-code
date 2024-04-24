package com.clover.recode.domain.statistics.controller;

import com.clover.recode.domain.statistics.service.StatisticsService;
import com.clover.recode.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.clover.recode.global.result.ResultCode.GET_Statistics_SUCCESS;

@Slf4j
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/")
    public ResponseEntity<ResultResponse> getStatisticsList(int id) {
        return ResponseEntity.ok(ResultResponse.of(GET_Statistics_SUCCESS, statisticsService.getStatisticsList(id)));
    }


}
