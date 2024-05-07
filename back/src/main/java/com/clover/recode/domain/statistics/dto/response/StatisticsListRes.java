package com.clover.recode.domain.statistics.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class StatisticsListRes {

    private int sequence;

    private int ranking;

    private List<Integer> weekReviews;

    private List<TodayProblemRes> todayProblems;

    private int supplementaryQuestion;

    private int randomQuestion;

    private AlgoReviewRes algoReview;

}