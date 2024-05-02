package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface StatisticsService {

    StatisticsListRes getStatisticsList(Long id, Authentication authentication);

    Integer getReviewCnt(Long id, Authentication authentication);

     List<TodayProblemRes> getReviews(Long id, Authentication authentication);

}
