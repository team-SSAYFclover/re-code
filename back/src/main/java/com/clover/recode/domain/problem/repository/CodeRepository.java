package com.clover.recode.domain.problem.repository;


import com.clover.recode.domain.problem.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {}