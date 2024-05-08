package com.clover.recode.domain.user.repository;

import java.sql.Time;
import java.util.List;

public interface UserRepositoryQuerydsl {

  List<String> findByFcmTokens(Time time);

}
