package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.statistics.entity.*;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsScheduler {

    private final CodeRepository codeRepository;
    private final TodayProblemRepository todayProblemRepository;
    private final StatisticsRepository statisticsRepository;
    private final JPAQueryFactory jpaQueryFactory;


    @Scheduled(cron = "0 0 15 * * *")
    @Transactional
    public void updateTodayProblem() {

            LocalDate today = LocalDate.now();

            List<Code> codesToReview = codeRepository.findByReviewStatusTrueAndReviewTimeBefore();

            for(Code code: codesToReview) {
                    TodayProblem todayProblem = TodayProblem.builder()
                            .isCompleted(false)
                            .reviewCnt(code.getRecode().getSubmitCount())
                            .code(code)
                            .title(code.getProblem().getTitle())
                            .date(today)
                            .problemNo(code.getProblem().getProblemNo())
                            .user(code.getUser())
                            .build();

                todayProblemRepository.save(todayProblem);
            }

    }

    @Scheduled(cron = "0 0 15 * * *")
    @Transactional
    public void updateRanking() {

        QStatistics statistics= QStatistics.statistics;
        QWeekReview weekReview= QWeekReview.weekReview;

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
                    .where(weekReview.statistics.id.eq(st.getId())
                            .and(weekReview.date.between(mon, today)))
                    .fetchOne();

            Integer ranking= (int) (100- (double) st_sum/total * 100);

            st.setRanking(ranking);


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

            statisticsRepository.save(st);


        }
    }

}
