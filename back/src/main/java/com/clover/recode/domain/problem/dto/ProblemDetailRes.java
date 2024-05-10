package com.clover.recode.domain.problem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ProblemDetailRes {

    private ProblemRes problemRes;
    private List<CodeRes> codeRes;

}
