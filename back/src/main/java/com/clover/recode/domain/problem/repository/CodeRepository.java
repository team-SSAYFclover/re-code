package com.clover.recode.domain.problem.repository;


import com.clover.recode.domain.problem.entity.Code;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long>, CodeCustomRepository {

  boolean existsByCodeNo(Integer codeNo);
  Optional<Code> findByIdAndUserId(Long Id, Long userId);

}