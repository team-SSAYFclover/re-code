package com.clover.recode.domain.statistics.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodayProblemDto {

    private int problemNo;

    private Long codeId;

    private String title;

    private int reviewCnt;

    private boolean isCompleted;

}
