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

    private Long id;

    private Long problem_id;

    private Long code_id;

    private String name;

    private boolean iScomplete;

    private int review_cnt;

}
