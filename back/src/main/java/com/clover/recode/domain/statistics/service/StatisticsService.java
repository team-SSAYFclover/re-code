package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;

public interface StatisticsService {

    StatisticsListRes getStatisticsList(Long id);

    Long getReviewCnt(Long id);

}
