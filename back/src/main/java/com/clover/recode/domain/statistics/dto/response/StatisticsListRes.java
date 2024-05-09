package com.clover.recode.domain.statistics.dto.response;

import com.clover.recode.domain.statistics.dto.AlgoReviewDto;
import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.dto.WeekReviewDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsListRes {

    private int sequence;

    private int ranking;

    private WeekReviewDto weekReviews;

    private List<TodayProblemDto> todayProblems;

    private int supplementaryQuestion;

    private int randomQuestion;

    private AlgoReviewDto algoReview;

}