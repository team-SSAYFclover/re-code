package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;

import java.util.List;

public interface StatisticsService {

    StatisticsListRes getStatisticsList(Long id);

    Long getReviewCnt(Long id);

     List<TodayProblemRes> getReviews(Long id);

}
