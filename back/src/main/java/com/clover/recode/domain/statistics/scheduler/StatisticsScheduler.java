package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.recode.entity.QRecode;
import com.clover.recode.domain.statistics.entity.QStatistics;
import com.clover.recode.domain.statistics.entity.QWeekReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticsScheduler {

    private final CodeRepository codeRepository;
    private final TodayProblemRepository todayProblemRepository;
    private final StatisticsRepository statisticsRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void updateTodayProblem() {

            LocalDate today = LocalDate.now();
            List<Code> codesToReview = codeRepository.findByReviewStatusFalseAndReviewTimeBefore(today);

            for(Code code: codesToReview) {
                    TodayProblem todayProblem = TodayProblem.builder()
                            .isCompleted(false)
                            //.reviewCnt(code.getRecode().getSubmit_count())
                            .code(code)
                            .name(code.getProblem().getTitle())
                            .date(today)
                            .build();

                todayProblemRepository.save(todayProblem);
            }

    }

    @Scheduled(cron = "0 05 17 * * *")
    @Transactional
    public void updateStatistics() {

        QRecode recode= QRecode.recode;
        QStatistics statistics= QStatistics.statistics;
        QWeekReview weekReview= QWeekReview.weekReview;

        //내 위치 백분율로 찾기
        //통계, 매주 복습량 테이블에서 전체 복습량 갯수에서
        //통계id별로 복습량 갯수의 rank()를 구한다

        Integer total= jpaQueryFactory
                .select(weekReview.count.sum())
                .from(weekReview)
                .fetchOne();

        List<Statistics> statisticsList= jpaQueryFactory.selectFrom(statistics)
                                        .fetch();

        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        for(Statistics st: statisticsList){

            Integer st_sum= jpaQueryFactory
                    .select(weekReview.count.sum())
                    .from(weekReview)
                    .where(weekReview.statistics.id.eq(st.getId()))
                    .where(weekReview.date.between(startOfWeek, endOfWeek))
                    .fetchOne();

            st.setRanking(st_sum/total * 100);


            statisticsRepository.save(st);

        }
    }
}
