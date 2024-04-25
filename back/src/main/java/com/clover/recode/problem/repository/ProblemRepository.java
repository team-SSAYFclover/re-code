package com.clover.recode.problem.repository;

import com.clover.recode.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    // 추가적인 쿼리 메소드를 필요에 따라 여기에 작성
}
