package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.WeekReviewDto;
import com.clover.recode.domain.statistics.entity.QWeekReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class WeekReviewCustomRepositoryImpl implements WeekReviewCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public WeekReviewDto findReviewsBetweenDates(LocalDate mon, LocalDate sun, Long statisticsId) {
        return null;
//        QWeekReview weeklyReview = QWeekReview.weekReview;
//        return jpaQueryFactory
//                .select(weeklyReview.count)
//                .from(weeklyReview)
//                .where(weeklyReview.statistics.id.eq(statisticsId))
//                .where(weeklyReview.date.between(mon, sun))
//                .orderBy(weeklyReview.date.asc()) // 날짜 순으로 오름차순 정렬
//                .fetch();

    }

    @Override
    public Integer countByTodayWeview(Long statisticsId, LocalDate today) {
        return null;
//        QWeekReview weekReview= QWeekReview.weekReview;
//
//        return jpaQueryFactory
//                .select(weekReview.count)
//                .from(weekReview)
//                .where(weekReview.date.eq(today))
//                .fetchOne();

    }


}
