package com.clover.recode.domain.recode.dto;

import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.entity.Problem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecodeRes {
    private ProblemDto problemDto;
    private String recode;
    private List<String> answers;
}
