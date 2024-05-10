package com.clover.recode.domain.statistics.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeekReviewDto {

    int mon;

    int tue;

    int wed;

    int thu;

    int fri;

    int sat;

    int sun;

}
