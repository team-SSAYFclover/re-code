package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.problem.entity.QProblem;
import com.clover.recode.domain.problem.entity.QTag;
import com.clover.recode.domain.problem.repository.CodeCustomRepository;
import com.clover.recode.domain.recode.entity.QRecode;
import com.clover.recode.domain.statistics.entity.*;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
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
                            .problemNo(code.getProblem().getProblemNo())
                            .user(code.getUser())
                            .build();

                todayProblemRepository.save(todayProblem);
            }

    }

    @Scheduled(cron = "0 36 22 * * *")
    @Transactional
    public void updateRanking() {

        QStatistics statistics= QStatistics.statistics;
        QWeekReview weekReview= QWeekReview.weekReview;
        QProblem qproblem= QProblem.problem;
        QCode qcode= QCode.code;

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


            statisticsRepository.save(st);

            //내가 풀지 않은 문제 중에서 랜덤문제를 가져온다
            List<Integer> unsolvedProblemNos = jpaQueryFactory
                    .select(qproblem.problemNo)
                    .from(qproblem)
                    .where(qproblem.problemNo.notIn(
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

            QTag qtag= QTag.tag;
            QAlgoReview qAlgoReview= QAlgoReview.algoReview;

            //복습한 문제 중에서 가장 cnt가 적은 부분을 가져온다
            AlgoReview algoReview= (AlgoReview) jpaQueryFactory
                    .selectFrom(qAlgoReview)
                    .where(qAlgoReview.statisticsId.eq(st.getId()))
                    .fetch();

            Map<String, Integer> map= new HashMap<>();
            map.put("math", algoReview.getMathCnt());
            map.put("implementation", algoReview.getImplementationCnt());
            map.put("greedy", algoReview.getGreedyCnt());
            map.put("string", algoReview.getStringCnt());
            map.put("data_structures", algoReview.getData_structuresCnt());
            map.put("graphs", algoReview.getGraphsCnt());
            map.put("dp", algoReview.getDpCnt());
            map.put("geometry", algoReview.getGeometryCnt());

            String leastAlgoCategory= algoReview.getMinFieldName(map);

            List<Integer> unsolvedAlgoProblem = jpaQueryFactory
                    .select(qproblem.problemNo)
                    .from(qproblem)
                    .join(qproblem.tags, qtag)
                    .where(qtag.name.eq(leastAlgoCategory)
                        .and(qproblem.problemNo.notIn(
                            JPAExpressions.select(qcode.problem.problemNo)
                                    .from(qcode)
                                    .where(qcode.user.id.eq(st.getUser().getId()))
                    )))
                    .fetch();

            Integer supplementary_question = -1;

            if (!unsolvedAlgoProblem.isEmpty()) {
                Collections.shuffle(unsolvedProblemNos);
                supplementary_question = unsolvedProblemNos.getFirst();
            }

            st.setSupplementaryNo(supplementary_question);

            statisticsRepository.save(st);

            
        }
    }

}
