package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.problem.entity.QProblem;
import com.clover.recode.domain.problem.repository.CodeCustomRepository;
import com.clover.recode.domain.statistics.entity.*;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

    @Scheduled(cron = "0 37 5 * * *")
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

    @Scheduled(cron = "0 36 22 * * *")
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

        log.info(total.toString());

        List<Statistics> statisticsList= jpaQueryFactory.selectFrom(statistics)
                                        .fetch();


        for(Statistics st: statisticsList){

            //랭킹을 구한다
            Integer st_sum= jpaQueryFactory
                    .select(weekReview.count.sum())
                    .from(weekReview)
                    .where(weekReview.statistics.id.eq(st.getId())
                            .and(weekReview.date.between(mon, today)))
                    .fetchOne();

            Integer ranking= (int) (100- (double) st_sum/total * 100);

            st.setRanking(ranking);


            statisticsRepository.save(st);

            List<Integer> test= jpaQueryFactory.select(problem.problemNo)
                    .from(qcode)
                    .where(qcode.user.id.eq(st.getUser().getId()))
                    .fetch();

            log.info("test 가보자고");
            for(int a: test){
                System.out.println("a: "+a);
            }

            //내가 풀지 않은 문제 중에서 랜덤문제를 가져온다
            List<Integer> unsolvedProblemNos = jpaQueryFactory
                    .select(problem.problemNo)
                    .from(problem)
                    .where(problem.problemNo.notIn(
                            JPAExpressions.select(qcode.problem.problemNo)
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

            //복습한 문제 중에서 가장 cnt가 적은 부분을 가져온다
            //알고리즘 분류가 적은 문제 중에서 내가 풀지 않은 문제의 id를 가져온다



            //연속복습일
            //어제 푼 문제가 없으면 0으로 초기화
            //사용자가 문제를 풀면 +1해주기
            LocalDate yesterday= LocalDate.now().minusDays(1);

                Integer isSolvedYesterday= jpaQueryFactory
                                .selectOne()
                                .from(weekReview)
                                .where(weekReview.date.eq(yesterday)
                                        .and(weekReview.statistics.id.eq(st.getId())))
                                .fetchFirst();

            
            if(isSolvedYesterday == null)
                st.setSequence(0);

            log.info("id: "+st.getId());
            log.info("시퀀스: "+st.getSequence());

            statisticsRepository.save(st);

            
        }
    }

}
