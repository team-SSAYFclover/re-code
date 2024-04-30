package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import com.clover.recode.domain.statistics.entity.*;
import com.clover.recode.domain.statistics.entity.QTodayProblem;
import com.clover.recode.domain.statistics.entity.QTodayReview;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TodayReviewRepositoryImpl implements TodayReviewRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TodayProblemRes> findTodayReviews(LocalDate date, Long statisticsId) {

        QTodayProblem todayProblem = QTodayProblem.todayProblem;
        QTodayReview todayReview = QTodayReview.todayReview;

        return jpaQueryFactory
                .select(Projections.constructor(TodayProblemRes.class,
                        todayProblem.id,
                        todayProblem.problem.id.as("problem_id"),
                        todayProblem.is_complete.as("isComplete"),
                        todayProblem.review_count.as("review_count")))
                .from(todayProblem)
                .join(todayProblem.todayReview, todayReview)
                .where(todayReview.date.eq(date))
                .where(todayReview.id.eq(statisticsId))
                .fetch();
    }

}
