package com.clover.recode.domain.recode.dto;

import com.clover.recode.domain.problem.entity.Problem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecodeRes {
    private Problem problem;
    private String recode;
    private List<String> answers;
}
