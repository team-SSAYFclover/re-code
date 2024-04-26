package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.TodayReview;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodayReviewRepository {

    List<TodayProblem> findTodayReviews(LocalDate date, Long id);

}
