package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.entity.Problem;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProblemRepository extends JpaRepository<Problem, Long> {


  Problem findFirstByProblemNo(Integer problemNo);

  Problem findProblemByProblemNo(Integer problemNo);


}

