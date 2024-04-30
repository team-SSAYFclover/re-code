package com.clover.recode.domain.recode.repository;

import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.entity.QProblem;
import com.clover.recode.domain.problem.entity.Tag;
import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import com.clover.recode.domain.statistics.entity.QTodayProblem;
import com.clover.recode.domain.statistics.entity.QTodayReview;
import com.clover.recode.domain.user.dto.SettingRes;
import com.clover.recode.domain.user.entity.QSetting;
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
    public ProblemDto findProblemByCodeId(int codeId) {

        QProblem problem = QProblem.problem;
        QCode code = QCode.code;
        QSetting setting = QSetting.setting;

        return jpaQueryFactory
                .select(Projections.constructor(ProblemDto.class,
                        todayProblem.id,
                        todayProblem.problem.id.as("problem_id"),
                        todayProblem.is_complete.as("isComplete"),
                        todayProblem.review_count.as("review_count")))
                .from(code)
                .join(code.problem, problem)
                .where(todayReview.date.eq(date))
                .fetch();
    }

    @Override
    public SettingRes findDifficultyByCodeId(int codeId) {
        return null;
    }

    @Override
    public List<Tag> findTagsByCodeId(int codeId) {
        return null;
    }
}
