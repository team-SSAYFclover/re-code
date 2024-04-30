package com.clover.recode.domain.recode.repository;

import com.clover.recode.domain.problem.dto.ProblemCodeDto;
import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.problem.entity.QProblem;
import com.clover.recode.domain.problem.entity.Tag;
import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import com.clover.recode.domain.statistics.entity.QTodayProblem;
import com.clover.recode.domain.statistics.entity.QTodayReview;
import com.clover.recode.domain.user.dto.SettingDto;
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
public class RecodeResRepositoryImpl implements RecodeResRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<TodayProblemRes> findTodayReviews(LocalDate date) {

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
                .fetch();
    }

    @Override
    public ProblemCodeDto findProblemByCodeId(int codeId) {

        QProblem problem = QProblem.problem;
        QCode code = QCode.code;

        return jpaQueryFactory
                .select(Projections.constructor(ProblemCodeDto.class,
                        problem.id,
                        todayProblem.problem.id.as("problem_id"),
                        todayProblem.is_complete.as("isComplete"),
                        todayProblem.review_count.as("review_count")))
                .from(code)
                .join(code.problem, problem)
                .where(todayReview.date.eq(date))
                .fetch();

        return null;
    }

    @Override
    public SettingDto findDifficultyByCodeId(int codeId) {
        return null;
    }

    @Override
    public List<Tag> findTagsByCodeId(int codeId) {
        return null;
    }
}
