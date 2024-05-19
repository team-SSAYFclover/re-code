package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.dto.CodeResList;
import com.clover.recode.domain.problem.entity.Code;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;


public interface CodeCustomRepository {

    List<Code> findByReviewStatusTrueAndReviewTimeBefore();

    //Page<Problem> findProblemsByUserId(Long userId, Pageable pageable, Integer start, Integer end, List<String> tags, String keyword);

    public List<CodeResList> findCodesByProblemNoAndUserId(Integer problemNo, Long userId);

    Code findCodeForAddReviewByCodeId(Long codeId, Long userId);
}
