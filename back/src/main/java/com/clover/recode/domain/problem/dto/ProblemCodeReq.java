package com.clover.recode.domain.problem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProblemCodeReq {
    private Long id;
    private ProblemDto problem;
    private CodeDto code;
}
