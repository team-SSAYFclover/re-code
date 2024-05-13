package com.clover.recode.domain.statistics.repository;

import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.problem.entity.QProblem;
import com.clover.recode.domain.problem.entity.QTag;
import com.clover.recode.domain.statistics.entity.AlgoReview;
import com.clover.recode.domain.statistics.entity.QAlgoReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.UserRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

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

        TreeMap<int[], String> map= new TreeMap<>((a, b) -> {
            if(a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        map.put(new int[] {0, algoReview.getMathCnt()}, "수학");
        map.put(new int[] {1, algoReview.getStringCnt()}, "문자열");
        map.put(new int[] {2, algoReview.getImplementationCnt()}, "구현");
        map.put(new int[] {3, algoReview.getData_structuresCnt()}, "자료 구조");
        map.put(new int[] {4, algoReview.getGraphsCnt()}, "그래프 이론");
        map.put(new int[] {5, algoReview.getDpCnt()}, "다이나믹 프로그래밍");
        map.put(new int[] {6, algoReview.getGreedyCnt()}, "그리디 알고리즘");
        map.put(new int[] {7, algoReview.getGeometryCnt()}, "기하학");

        Integer unsolvedAlgoProblem = null;

        while(!map.isEmpty() && unsolvedAlgoProblem == null) {
            unsolvedAlgoProblem = jpaQueryFactory
                    .select(qproblem.problemNo)
                    .from(qproblem)
                    .join(qproblem.tags, qtag)
                    .where(qtag.name.eq(map.pollFirstEntry().getValue())
                            .and(qproblem.problemNo.notIn(
                                    JPAExpressions.select(qcode.problem.problemNo)
                                            .from(qcode)
                                            .where(qcode.user.id.eq(userId))
                            )))
                    .orderBy(Expressions.numberTemplate(Double.class, "RAND()").asc())
                    .fetchOne();
        }

        return unsolvedAlgoProblem;

    }


}
