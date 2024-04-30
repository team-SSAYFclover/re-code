package com.clover.recode.domain.statistics.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodayProblemRes {

    private Long problemId;

    private Long codeId;

    private String name;

    private int reviewCnt;

    private boolean isCompleted;

}
