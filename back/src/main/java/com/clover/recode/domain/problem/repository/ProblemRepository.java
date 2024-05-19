package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.entity.Problem;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProblemRepository extends JpaRepository<Problem, Long>, ProblemCustomRepository {


  Problem findFirstByProblemNo(Integer problemNo);

  Optional<Problem> findProblemByProblemNo(Integer problemNo);


}

