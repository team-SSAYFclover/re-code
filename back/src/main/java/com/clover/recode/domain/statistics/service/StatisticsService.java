package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface StatisticsService {

    StatisticsListRes getStatisticsList(Authentication authentication);

    Long getReviewCnt(Authentication authentication);

     List<TodayProblemDto> getReviews(Authentication authentication);

}
