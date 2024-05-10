package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.Statistics;
import org.springframework.security.core.Authentication;

public interface StatisticsCustomRepository {

    Integer updateRandom(Long userId);

    Integer updateSupplement(Long userId, Statistics st);
}
