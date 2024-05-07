package com.clover.recode.domain.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodayProblemDto {

    private Long problemId;

    private Long codeId;

    private String name;

    private int reviewCnt;

    private boolean isCompleted;

}
