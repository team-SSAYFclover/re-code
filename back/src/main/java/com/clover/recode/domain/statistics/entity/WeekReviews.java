package com.clover.recode.domain.statistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class WeekReviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int count;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "statistic_id")
    private Statistics statistics;

}
