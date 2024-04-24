package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.WeekReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekReviewsRepository extends JpaRepository<WeekReviews, Integer> {
}
