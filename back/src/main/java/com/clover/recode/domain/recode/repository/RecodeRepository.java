package com.clover.recode.domain.recode.repository;

import com.clover.recode.domain.recode.entity.Recode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecodeRepository extends JpaRepository<Recode, Long>, RecodeResRepository {

}
