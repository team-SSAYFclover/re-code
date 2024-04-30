package com.clover.recode.domain.recode.repository;

import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.entity.Tag;
import com.clover.recode.domain.user.dto.SettingRes;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecodeResRepository {

    ProblemDto findProblemByCodeId(int codeId);
    SettingRes findDifficultyByCodeId(int codeId);
    List<Tag> findTagsByCodeId(int codeId);

}
