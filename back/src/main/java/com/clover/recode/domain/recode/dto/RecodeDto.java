package com.clover.recode.domain.recode.dto;

import com.clover.recode.domain.problem.dto.ProblemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecodeDto {
    private ProblemDto problem;     // 문제
    private String recode;          // 레코드
}
