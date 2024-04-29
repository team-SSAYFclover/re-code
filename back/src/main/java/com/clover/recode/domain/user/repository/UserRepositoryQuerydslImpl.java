package com.clover.recode.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRepositoryQuerydslImpl implements UserRepositoryQuerydsl {

  private final JPAQueryFactory jpaQueryFactory;


}
