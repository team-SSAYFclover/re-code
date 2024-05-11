package com.clover.recode.domain.problem.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor
public class ProblemDetailRes {

    private ProblemResList problemResList;
    private String content;
    private List<CodeResList> codeReLists;

}
