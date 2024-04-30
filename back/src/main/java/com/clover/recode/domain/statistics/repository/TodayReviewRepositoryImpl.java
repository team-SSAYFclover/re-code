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
    public List<TodayProblemRes> findTodayReviews(Long statisticsId) {

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
                .fetch();
    }

    @Override
    public Long countByStatisticsIdAndDate(Long statisticsId, LocalDate date) {
        QTodayReview todayReview = QTodayReview.todayReview;
        return jpaQueryFactory.select(todayReview.count())
                .from(todayReview)
                .where(todayReview.statistics.id.eq(statisticsId)
                        .and(todayReview.date.eq(date)))
                .fetchOne();
    }

}
