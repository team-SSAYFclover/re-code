package com.clover.recode.domain.user.repository;

import com.clover.recode.domain.user.dto.idRes;
import com.clover.recode.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQuerydsl {
  User findByGithubId(Long githubId);
  Optional<idRes> findByUuid(String uuid);

}
