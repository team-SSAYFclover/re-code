package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProblemCustomRepository {
    public Page<Problem> findProblemsByUserId(Long userId, Pageable pageable, Integer start, Integer end, List<String> tags, String keyword);
    //public Page<ProblemRes> findProblemsByUserId(Authentication authentication, Pageable pageable, Integer start, Integer end, List<String> tags, String keyword);
    public Integer getReviewCount(Long problemId, Long userId);
    //public List<Code> findCodeByProblemNo(Integer problemNo);

}
