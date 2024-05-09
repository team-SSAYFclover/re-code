package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.entity.QWeekReview;
import com.clover.recode.domain.statistics.entity.WeekReview;
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
public class WeekReviewCustomRepositoryImpl implements WeekReviewCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Integer> findReviewsBetweenDates(LocalDate mon, LocalDate sun, Long statisticsId) {
        QWeekReview weeklyReview = QWeekReview.weekReview;

        // 월요일부터 일요일까지의 데이터만 조회
        List<WeekReview> reviews = jpaQueryFactory
                .selectFrom(weeklyReview)
                .where(weeklyReview.date.between(mon, sun)
                        .and(weeklyReview.statistics.id.eq(statisticsId)))
                .fetch();

        // 리뷰 수 리스트 생성
        List<Integer> reviewCounts = new ArrayList<>();

        log.info("왜 오늘꺼는 조회가 안될까?");

        for(WeekReview weekReview: reviews)
            log.info("id: "+weekReview.getId()+" 날짜: "+weekReview.getDate());

        // 각 날짜의 리뷰 수 계산 및 리스트에 추가
        for (LocalDate date = mon; date.isBefore(sun.plusDays(1)); date = date.plusDays(1)) {
            int count = 0;
            for (WeekReview review : reviews) {
                if (review.getDate().equals(date)) {
                    count = review.getCount();
                    break;
                }
            }
            reviewCounts.add(count);
        }

        return reviewCounts;

    }

    @Override
    public Integer countByTodayReview(Long statisticsId) {
        QWeekReview weekReview= QWeekReview.weekReview;
        LocalDate today = LocalDate.now();

        return jpaQueryFactory
                .select(weekReview.count)
                .from(weekReview)
                .where(weekReview.date.eq(today)
                        .and(weekReview.statistics.id.eq(statisticsId)))
                .fetchOne();
    }
}
