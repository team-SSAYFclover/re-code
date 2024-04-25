package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.QWeekReviews;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Slf4j
public class WeekReviewsRepositoryImpl implements WeekReviewsRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Integer> findReviewsBetweenDates(LocalDate mon, LocalDate sun) {
        QWeekReviews weeklyReview = QWeekReviews.weekReviews;
        return jpaQueryFactory
                .select(weeklyReview.count)
                .from(weeklyReview)
                .where(weeklyReview.date.between(mon, sun))
                .orderBy(weeklyReview.date.asc()) // 날짜 순으로 오름차순 정렬
                .fetch();

    }

}
