package com.clover.recode.domain.problem.entity;

import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.WeekReviews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "problem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer problemNo;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

}
