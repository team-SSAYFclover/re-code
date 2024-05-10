package com.clover.recode.domain.problem.service;

import com.clover.recode.domain.problem.dto.ProblemCodeRegistReq;
import com.clover.recode.domain.problem.dto.ProblemDetailRes;
import com.clover.recode.domain.problem.dto.ProblemRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;
public interface ProblemService {

    void saveProblemAndCode(ProblemCodeRegistReq problemCodeRegistReq);

    //사용자별 문제 조회
    Page<ProblemRes> findProblemsByUserId(Authentication authentication, Pageable pageable, Integer start, Integer end, List<String> tags, String keyword);

    public Integer getReviewCount(Long problemId);
    public List<String> getTagNames(Long problemId);

    public ProblemDetailRes getProblemDetails(Authentication authentication, Integer problemNo);


}