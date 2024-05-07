package com.clover.recode.domain.statistics.dto.response;

import com.clover.recode.domain.statistics.dto.AlgoReviewDto;
import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.dto.WeekReviewDto;
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

    private List<WeekReviewDto> weekReviews;

    private List<TodayProblemDto> todayProblems;

    private int supplementaryQuestion;

    private int randomQuestion;

    private AlgoReviewDto algoReview;

}