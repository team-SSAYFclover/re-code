package com.clover.recode.domain.recode.dto;

import com.clover.recode.domain.problem.dto.ProblemRegistReq;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecodeRes {
    private ProblemRegistReq problemRegistReq;
    private String recode;
    private List<String> answers;
}
