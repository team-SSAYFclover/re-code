package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.dto.CodeDTO;
import com.clover.recode.domain.problem.entity.Code;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CodeCustomRepository {

    List<CodeDTO> findByReviewStatusFalseAndReviewTimeBefore(Long userId, LocalDate today);

}
