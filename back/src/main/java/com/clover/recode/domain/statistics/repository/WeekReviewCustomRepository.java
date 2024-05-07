package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.WeekReviewDto;

import java.time.LocalDate;
import java.util.List;

public interface WeekReviewCustomRepository {


    List<Integer> findReviewsBetweenDates(LocalDate startOfWeek, LocalDate endOfWeek, Long statisticsId);

    Integer countByTodayWeview(Long statisticsId, LocalDate today);


}
