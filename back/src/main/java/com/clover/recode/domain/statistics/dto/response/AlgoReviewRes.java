package com.clover.recode.domain.statistics.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@NoArgsConstructor
public class AlgoReviewRes {

    private Integer math;

    private Integer implementation;

    private Integer greedy;

    private Integer string;

    private Integer data_structures;

    private Integer graphs;

    private Integer dp;

    private Integer geometry;
}
