package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Integer>, WeekReviewRepository, TodayReviewRepository {
}
