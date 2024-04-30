package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
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
    public List<TodayProblemRes> findTodayReviews(Long statisticsId, LocalDate today) {

        QTodayProblem todayProblem = QTodayProblem.todayProblem;
        QTodayReview todayReview = QTodayReview.todayReview;

        return jpaQueryFactory
                .select(Projections.constructor(TodayProblemRes.class,
                        todayProblem.code.problemId,
                        todayProblem.code.id,
                        todayProblem.code.name,
                        todayProblem.reviewCnt,
                        todayProblem.isCompleted
                        ))
                .from(todayProblem)
                .join(todayProblem.todayReview, todayReview)
                .where(todayReview.id.eq(statisticsId))
                .where(todayReview.date.eq(today))
                .fetch();
    }

    @Override
    public Long countByStatisticsIdAndDate(Long statisticsId, LocalDate today) {
        QTodayReview todayReview = QTodayReview.todayReview;
        QTodayProblem todayProblem= QTodayProblem.todayProblem;

        return jpaQueryFactory.select(todayProblem.count())
                .from(todayProblem)
                .join(todayProblem.todayReview, todayReview)
                .where(todayReview.statistics.id.eq(statisticsId)
                        .and(todayReview.date.eq(today)))
                .fetchOne();
    }

}
