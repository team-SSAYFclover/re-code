package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.TodayProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayProblemRepository extends JpaRepository<TodayProblem, Long> {
}
