package com.clover.recode.domain.statistics.dto.response;

import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.TodayReview;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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

}
