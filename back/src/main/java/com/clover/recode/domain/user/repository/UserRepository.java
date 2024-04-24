package com.clover.recode.domain.user.repository;

import com.clover.recode.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findBygithubId(long githubId);

}
