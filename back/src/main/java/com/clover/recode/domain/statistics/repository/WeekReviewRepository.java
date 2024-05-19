package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.WeekReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeekReviewRepository extends JpaRepository<WeekReview, Long>, WeekReviewCustomRepository {
}
