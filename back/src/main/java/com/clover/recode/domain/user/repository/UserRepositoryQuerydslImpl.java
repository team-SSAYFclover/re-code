  package com.clover.recode.domain.user.repository;

  import com.clover.recode.domain.fcmtoken.entity.QFcmToken;
  import com.clover.recode.domain.user.entity.QSetting;
  import com.clover.recode.domain.user.entity.QUser;
  import com.querydsl.jpa.impl.JPAQueryFactory;
  import java.sql.Time;
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

      return jpaQueryFactory.select(fcmToken.token)
          .from(fcmToken)
          .join(fcmToken.user, user)
          .join(user.setting, setting)
          .where(
              setting.notificationTime.eq(time),
              setting.notificationStatus.isTrue()
          )
          .fetch();
    }
  }
