package com.clover.recode.domain.statistics.entity;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TodayProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean is_complete;

    private int review_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "today_review_id")
    private TodayReview todayReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id")
    private Code code;


    @Override
    public String toString() {
        return id + " " + is_complete;
    }

}
