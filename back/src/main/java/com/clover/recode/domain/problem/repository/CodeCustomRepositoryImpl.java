package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.QCode;
import com.clover.recode.domain.recode.entity.QRecode;
import com.clover.recode.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CodeCustomRepositoryImpl implements CodeCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Code> findByReviewStatusFalseAndReviewTimeBefore(Long userId, LocalDate today) {

        QUser user= QUser.user;
        QCode code= QCode.code;
        QRecode recode= QRecode.recode;

        return jpaQueryFactory.select(code)
                .join(recode, code.recode)
                .join(user, code.user)
                .where(code.user.id.eq(userId))
                .where(code.reviewStatus.eq(false))
                .where(recode.review_time.before(today))
                .fetch();
    }
}
