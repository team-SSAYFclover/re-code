package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.QAlgoReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AlgoReviewRepositoryImpl implements AlgoReviewRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Integer> findAlgoReviewList() {

        QAlgoReview qAlgoReview= QAlgoReview.algoReview;

        return jpaQueryFactory
                .select(qAlgoReview.count)
                .from(qAlgoReview)
                .orderBy(qAlgoReview.id.asc())
                .fetch();

    }
}
