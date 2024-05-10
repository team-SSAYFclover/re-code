package com.clover.recode.domain.recode.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecodeRes {
    private ProblemDto problem;
    private String recode;
    private List<String> answers;
}
