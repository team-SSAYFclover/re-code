package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.StatisticProblemDTO;
import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface StatisticsService {

    StatisticsListRes getStatisticsList(Authentication authentication);

    Integer getReviewCnt(Long userId);

     List<TodayProblemDto> getReviews(Authentication authentication);

    StatisticProblemDTO updateRandom(Authentication authentication);

    StatisticProblemDTO updateSupplement(Authentication authentication);
}
