package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.entity.TodayProblem;

import java.util.List;

public interface TodayProblemCustomRepository {

    List<TodayProblemDto> findByUserId(Long userId);

}
