package com.clover.recode.domain.problem.repository;
import com.clover.recode.domain.problem.dto.CodeResList;
import com.clover.recode.domain.problem.entity.*;
import com.clover.recode.domain.recode.entity.QRecode;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
public class CodeCustomRepositoryImpl implements CodeCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Code> findByReviewStatusTrueAndReviewTimeBefore() {

        LocalDate today= LocalDate.now();
        QRecode recode= QRecode.recode;
        QCode code= QCode.code;
        QProblem problem = QProblem.problem;

        return jpaQueryFactory.selectFrom(code)
                .innerJoin(code.recode, recode)
                .fetchJoin()
                .innerJoin(code.problem, problem)
                .fetchJoin()
                .where(code.deleted.eq(false),
                    code.reviewStatus.eq(true),
                        recode.reviewTime.before(today.atStartOfDay().plusDays(1)))
                .fetch();

    }

    @Transactional(readOnly = true)
    public List<Code> findCodeByProblemNo(Integer problemNo) {
        QCode qCode = QCode.code;
        QProblem qProblem = QProblem.problem;

        // 조인과 필터 조건을 사용한 쿼리 구성
        List<Code> codes = jpaQueryFactory
                .selectFrom(qCode)
                .fetchJoin()
                .join(qCode.problem, qProblem)
                .where(qProblem.problemNo.eq(problemNo))
                .fetch();
        return codes;
    }



    @Transactional(readOnly = true)
    public List<CodeResList> findCodesByProblemNoAndUserId(Integer problemNo, Long userId) {
        QCode qCode = QCode.code;
        QProblem qProblem = QProblem.problem;
        QRecode qRecode = QRecode.recode;

        return jpaQueryFactory
                .select(Projections.constructor(CodeResList.class,
                        qCode.id, qCode.name, qCode.content, qCode.createdTime, qCode.reviewStatus))
                .from(qCode)
                .join(qCode.problem, qProblem)
                .where(qProblem.problemNo.eq(problemNo)
                        .and(qCode.user.id.eq(userId)))
                .orderBy(qCode.createdTime.desc())
                .fetch();
    }

    @Override
    public Code findCodeForAddReviewByCodeId(Long codeId, Long userId) {
        QCode code = QCode.code;
        QProblem problem = QProblem.problem;
        QRecode recode = QRecode.recode;

        return jpaQueryFactory.selectFrom(code)
            .join(code.recode, recode).fetchJoin()
            .join(code.problem, problem).fetchJoin()
            .where(
                code.id.eq(codeId),
                code.user.id.eq(userId)
            )
            .fetchOne();
    }
}
