package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.TodayReview;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodayReviewRepository {

    List<TodayProblemRes> findTodayReviews(Long statisticsId);

    Long countByStatisticsIdAndDate(Long statisticsId, LocalDate date);
}
