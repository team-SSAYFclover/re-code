package com.clover.recode.domain.problem.dto;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;

@Getter
@Setter
@ToString
public class ProblemCodeRegistReq {
    private Long id;
    private ProblemRegistReq problem;
    private CodeRegistReq code;
}
