package com.clover.recode.domain.statistics.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class StatisticsListRes {

    private int sequence;

    private int rank;

    private Map<String, Integer> weekReviews;

    private List<TodayReview> todayReviews;

    private int supplementaryQuestion;

    private int randomQuestion;

    public static class TodayReview {
        private int id;
        private String name;
        private String isCompleted;
        private int cnt;
    }
}
