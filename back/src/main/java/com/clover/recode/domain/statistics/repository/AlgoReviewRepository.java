package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.AlgoReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgoReviewRepository extends JpaRepository<AlgoReview, Long> {

}
