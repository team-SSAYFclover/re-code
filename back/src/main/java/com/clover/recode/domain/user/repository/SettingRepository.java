package com.clover.recode.domain.user.repository;

import com.clover.recode.domain.user.entity.Setting;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.List;
public interface SettingRepository extends JpaRepository<Setting, Long> {

    //JPQL
    @EntityGraph(attributePaths = {"user"})
    @Query("select s from Setting s where s.notificationStatus = true and s.notificationTime = :notificationTime")
    List<Setting> findSettingsByNotificationTime(@Param("notificationTime") Time notificationTime);

    //쿼리 메서드
//    List<Setting> findByNotificationTimeAndNotificationStatusIsTrue(Time notificationTime);




}
