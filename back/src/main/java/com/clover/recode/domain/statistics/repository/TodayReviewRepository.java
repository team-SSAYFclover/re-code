package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import com.clover.recode.domain.statistics.entity.TodayReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TodayReviewRepository extends JpaRepository<TodayReview, Long> {


}
