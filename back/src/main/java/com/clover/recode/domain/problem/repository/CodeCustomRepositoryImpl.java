package com.clover.recode.domain.problem.repository;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.recode.entity.QRecode;
import com.clover.recode.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CodeCustomRepositoryImpl implements CodeCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Code> findByReviewStatusFalseAndReviewTimeBefore() {

        LocalDate today= LocalDate.now();
        QRecode recode= QRecode.recode;
        QCode code= QCode.code;

        return jpaQueryFactory.selectFrom(code)
                .where(code.deleted.eq(false),
                    code.reviewStatus.eq(true),
                        recode.reviewTime.before(today.atStartOfDay().plusDays(1)))
                .fetch();

    }
}
