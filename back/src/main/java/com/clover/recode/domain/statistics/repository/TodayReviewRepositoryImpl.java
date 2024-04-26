package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.*;
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
    public List<TodayProblem> findTodayReviews(LocalDate date, Long id) {
        QTodayProblem todayProblem = QTodayProblem.todayProblem;
        QTodayReview todayReview = QTodayReview.todayReview;
        QStatistics statistics = QStatistics.statistics;

        return jpaQueryFactory
                .selectFrom(todayProblem)
                .join(todayProblem.todayReview, todayReview) // TodayProblem과 TodayReview를 조인
                .join(todayReview.statistics, statistics) // TodayReview와 User를 조인
                .where(todayReview.date.eq(date)
                        .and(statistics.id.eq(id))) // 조건: 오늘날짜 및 통계ID
                .fetch();
    }

}
