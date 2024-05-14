package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.entity.QTodayProblem;
import com.clover.recode.domain.statistics.entity.TodayProblem;
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
public class TodayProblemCustomRepositoryImpl implements TodayProblemCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TodayProblemDto> findByUserId(Long userId, LocalDate day) {

        QTodayProblem todayProblem= QTodayProblem.todayProblem;

        return jpaQueryFactory.select(
                Projections.constructor(
                        TodayProblemDto.class,
                        todayProblem.problemNo,
                        todayProblem.code.id,
                        todayProblem.title,
                        todayProblem.reviewCnt,
                        todayProblem.isCompleted
                )
                )
                .from(todayProblem)
                .where(todayProblem.date.eq(day)
                        .and((todayProblem.user.id.eq(userId))))
                .fetch();

    }
}
