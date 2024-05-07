package com.clover.recode.domain.statistics.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@NoArgsConstructor
public class AlgoReviewDto {

    private int math;

    private int implementation;

    private int greedy;

    private int string;

    private int data_structures;

    private int graphs;

    private int dp;

    private int geometry;
}
