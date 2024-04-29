package com.clover.recode.domain.statistics.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgoReviewRepository {

    List<Integer> findAlgoReviewList();
}
