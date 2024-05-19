  package com.clover.recode.domain.user.repository;

  import com.clover.recode.domain.fcmtoken.entity.QFcmToken;
  import com.clover.recode.domain.statistics.entity.QTodayProblem;
  import com.clover.recode.domain.user.entity.QSetting;
  import com.clover.recode.domain.user.entity.QUser;
  import com.querydsl.jpa.JPAExpressions;
  import com.querydsl.jpa.impl.JPAQueryFactory;
  import java.sql.Time;
  import java.time.LocalDate;
  import java.time.LocalDateTime;
  import java.util.List;
  import lombok.AllArgsConstructor;

  @AllArgsConstructor
  public class UserRepositoryQuerydslImpl implements UserRepositoryQuerydsl {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<String> findByFcmTokens(Time time) {

      QUser user = QUser.user;
      QFcmToken fcmToken = QFcmToken.fcmToken;
      QSetting setting = QSetting.setting;
      QTodayProblem todayProblem = QTodayProblem.todayProblem;

      LocalDate day;
      if(LocalDateTime.now().getHour() < 4) day= LocalDate.now().minusDays(1);
      else day= LocalDate.now();

      return jpaQueryFactory.select(fcmToken.token)
          .from(fcmToken)
          .join(fcmToken.user, user)
          .join(user.setting, setting)
          .where(
              setting.notificationTime.eq(time),
              setting.notificationStatus.isTrue(),

              user.in(
                  JPAExpressions
                      .select(todayProblem.user).distinct()
                      .from(todayProblem)
                      .where(
                          todayProblem.isCompleted.isFalse(),
                          todayProblem.date.eq(day)
                      )

              )

          )
          .fetch();
    }
  }
