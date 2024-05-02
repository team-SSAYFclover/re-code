package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.dto.CodeDTO;
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
    public List<CodeDTO> findByReviewStatusFalseAndReviewTimeBefore(Long userId, LocalDate today) {

        QUser user= QUser.user;
        QCode code= QCode.code;
        QRecode recode= QRecode.recode;

        return jpaQueryFactory.selectFrom(code)
                .join(code.recode, recode)
                .join(code.user, user)
                .where(code.user.id.eq(userId),
                    code.deleted.eq(false),
                    code.reviewStatus.eq(true),
                    recode.review_time.before(today.plusDays(1)))
                .fetch();
    }
}
