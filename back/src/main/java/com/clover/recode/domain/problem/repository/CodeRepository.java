package com.clover.recode.domain.problem.repository;


import com.clover.recode.domain.problem.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface CodeRepository extends JpaRepository<Code, Long> {

}