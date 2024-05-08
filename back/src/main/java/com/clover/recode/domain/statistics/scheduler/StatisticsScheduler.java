package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.problem.entity.QProblem;
import com.clover.recode.domain.problem.repository.CodeCustomRepository;
import com.clover.recode.domain.recode.entity.QRecode;
import com.clover.recode.domain.statistics.entity.QStatistics;
import com.clover.recode.domain.statistics.entity.QWeekReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.clover.recode.domain.user.entity.QUser;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.UserRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticsScheduler {

    private final CodeCustomRepository codeCustomRepository;
    private final TodayProblemRepository todayProblemRepository;
    private final StatisticsRepository statisticsRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void updateTodayProblem() {

            LocalDate today = LocalDate.now();
            List<Code> codesToReview = codeCustomRepository.findByReviewStatusFalseAndReviewTimeBefore();

            for(Code code: codesToReview) {
                    TodayProblem todayProblem = TodayProblem.builder()
                            .isCompleted(false)
                            .reviewCnt(code.getRecode().getSubmitCount())
                            .code(code)
                            .title(code.getProblem().getTitle())
                            .date(today)
                            .build();

                todayProblemRepository.save(todayProblem);
            }

    }

    @Scheduled(cron = "0 05 17 * * *")
    @Transactional
    public void updateRanking() {

        QStatistics statistics= QStatistics.statistics;
        QWeekReview weekReview= QWeekReview.weekReview;
        QProblem problem= QProblem.problem;
        QCode  qcode= QCode.code;

        //내 위치 백분율로 찾기
        //통계, 매주 복습량 테이블에서 전체 복습량 갯수에서
        //통계id별로 복습량 갯수의 rank()를 구한다

        LocalDate mon = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate today= LocalDate.now();

        Integer total= jpaQueryFactory
                .select(weekReview.count.sum())
                .where(weekReview.date.between(mon, today))
                .from(weekReview)
                .fetchOne();

        List<Statistics> statisticsList= jpaQueryFactory.selectFrom(statistics)
                                        .fetch();


        for(Statistics st: statisticsList){

            //랭킹을 구한다
            Integer st_sum= jpaQueryFactory
                    .select(weekReview.count.sum())
                    .from(weekReview)
                    .where(weekReview.statistics.id.eq(st.getId()))
                    .where(weekReview.date.between(mon, today))
                    .fetchOne();

            st.setRanking(st_sum/total * 100);

            //내가 풀지 않은 문제 중에서 랜덤문제를 가져온다
            List<Integer> unsolvedProblemNos = jpaQueryFactory
                    .select(problem.problemNo)
                    .from(problem)
                    .where(problem.problemNo.notIn(
                            JPAExpressions.select(problem.problemNo)
                                    .from(qcode)
                                    .where(qcode.user.id.eq(st.getUser().getId()))
                    ))
                    .fetch();

            Integer randomNo = -1;

            if (!unsolvedProblemNos.isEmpty()) {
                Collections.shuffle(unsolvedProblemNos);
                randomNo = unsolvedProblemNos.getFirst();
            }

            st.setRandomNo(randomNo);


        }
    }

}
