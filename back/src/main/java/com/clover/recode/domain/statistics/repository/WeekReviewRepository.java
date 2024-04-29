package com.clover.recode.domain.statistics.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeekReviewsRepository  {

    List<Integer> findReviewsBetweenDates(LocalDate startOfWeek, LocalDate endOfWeek);

}
