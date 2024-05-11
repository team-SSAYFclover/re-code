package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface StatisticsRepository extends JpaRepository<Statistics, Long>, StatisticsCustomRepository{

    Statistics findByUserId(Long userId);
}
