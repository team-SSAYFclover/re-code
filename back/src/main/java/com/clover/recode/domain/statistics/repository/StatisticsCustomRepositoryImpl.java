package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.problem.entity.QProblem;
import com.clover.recode.domain.problem.entity.QTag;
import com.clover.recode.domain.statistics.entity.AlgoReview;
import com.clover.recode.domain.statistics.entity.QAlgoReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.UserRepository;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class StatisticsCustomRepositoryImpl implements StatisticsCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer updateRandom(Long userId) {
        //내가 풀지 않은 문제 중에서 랜덤문제를 가져온다

        QProblem qproblem= QProblem.problem;
        QCode qcode= QCode.code;

        List<Integer> unsolvedProblemNos = jpaQueryFactory
                .select(qproblem.problemNo)
                .from(qproblem)
                .where(qproblem.problemNo.notIn(
                        JPAExpressions.select(qcode.problem.problemNo)
                                .from(qcode)
                                .where(qcode.user.id.eq(userId))
                ))
                .fetch();

        Integer randomNo = -1;

        if (!unsolvedProblemNos.isEmpty()) {
            Collections.shuffle(unsolvedProblemNos);
            randomNo = unsolvedProblemNos.getFirst();
        }

        return randomNo;
    }

    @Override
    public Integer updateSupplement(Long userId, Statistics st) {

        QTag qtag= QTag.tag;
        QAlgoReview qAlgoReview= QAlgoReview.algoReview;
        QProblem qproblem= QProblem.problem;
        QCode qcode= QCode.code;


        //복습한 문제 중에서 가장 cnt가 적은 부분을 가져온다
        AlgoReview algoReview= jpaQueryFactory
                .selectFrom(qAlgoReview)
                .where(qAlgoReview.statisticsId.eq(st.getId()))
                .fetchOne();

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

        log.info("제일 작은 값: "+ algoReview.getMinFieldName(map));

        List<Integer> unsolvedAlgoProblem = jpaQueryFactory
                .select(qproblem.problemNo)
                .from(qproblem)
                .join(qproblem.tags, qtag)
                .where(qtag.name.eq(leastAlgoCategory)
                        .and(qproblem.problemNo.notIn(
                                JPAExpressions.select(qcode.problem.problemNo)
                                        .from(qcode)
                                        .where(qcode.user.id.eq(userId))
                        )))
                .fetch();

        Integer supplementary_question = -1;


        if (!unsolvedAlgoProblem.isEmpty()) {
            Collections.shuffle(unsolvedAlgoProblem);
            supplementary_question = unsolvedAlgoProblem.getFirst();
        }
        return supplementary_question;

    }
}
