package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import org.springframework.stereotype.Service;

public interface StatisticsService {

    StatisticsListRes getStatisticsList(int id);

}
