package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.StatisticProblemDTO;
import com.clover.recode.domain.statistics.entity.Statistics;

public interface StatisticsCustomRepository {

    StatisticProblemDTO updateRandom(Long userId);

    StatisticProblemDTO updateSupplement(Long userId, Statistics st);
}
