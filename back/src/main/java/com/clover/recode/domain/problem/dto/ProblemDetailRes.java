package com.clover.recode.domain.problem.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor
public class ProblemDetailRes {

    private ProblemRes problemRes;
    private List<CodeRes> codeRes;

}
