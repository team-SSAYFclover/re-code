package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface CodeCustomRepository {

    List<Code> findByReviewStatusFalseAndReviewTimeBefore(LocalDate today);
//    List<Problem> findProblemsByUserId(Long userId);

    Page<Problem> findProblemsByUserId(Long userId, Pageable pageable, Integer start, Integer end, List<String> tags, String keyword);
}
