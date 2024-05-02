package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodayReviewCustomRepository{

    public List<TodayProblemRes> findTodayReviews(Long statisticsId, LocalDate today);
}
