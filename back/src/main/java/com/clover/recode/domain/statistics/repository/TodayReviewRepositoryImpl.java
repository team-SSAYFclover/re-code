package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.recode.entity.QRecode;
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
public class TodayReviewRepositoryImpl implements TodayReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TodayProblemRes> findTodayReviews(Long statisticsId, LocalDate today) {

        QTodayProblem todayProblem = QTodayProblem.todayProblem;
        QTodayReview todayReview = QTodayReview.todayReview;

        return jpaQueryFactory
                .select(Projections.constructor(TodayProblemRes.class,
                        todayProblem.code.problem.id,
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
}
