package com.clover.recode.domain.problem.repository;

import com.clover.recode.domain.problem.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Byte> {

  Tag findByName(String name);

}
