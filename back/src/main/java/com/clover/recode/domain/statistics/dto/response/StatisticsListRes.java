package com.clover.recode.domain.statistics.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class StatisticsListRes {

    private int sequence;

    private int ranking;

    private List<Integer> weekReviews;

    //private List<TodayReview> todayReviews;

    private int supplementaryQuestion;

    private int randomQuestion;

    public static class TodayReview {
        private int id;
        private String name;
        private String isCompleted;
        private int cnt;
    }
}
