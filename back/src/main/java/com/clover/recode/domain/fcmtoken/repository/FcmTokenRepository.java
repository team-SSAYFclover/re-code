package com.clover.recode.domain.fcmtoken.repository;

import com.clover.recode.domain.fcmtoken.entity.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
}