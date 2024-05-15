package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodayProblemRepository extends JpaRepository<TodayProblem, Long>, TodayProblemCustomRepository {
    Optional<TodayProblem> findByCodeIdAndDate(Long codeId, LocalDate day);

    Boolean existsByCodeIdAndUserIdAndDate(Long codeId, Long userId,LocalDate date);

    Integer countByDateAndUserIdAndIsCompletedFalse(LocalDate date, Long userId);
}
