package com.clover.recode.domain.recode.repository;

import com.clover.recode.domain.problem.dto.ProblemCodeDto;
import com.clover.recode.domain.problem.entity.Tag;
import com.clover.recode.domain.user.dto.SettingDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecodeResRepository {

    ProblemCodeDto findProblemByCodeId(int codeId);
    SettingDto findDifficultyByCodeId(int codeId);
    List<Tag> findTagsByCodeId(int codeId);

}
