package com.clover.recode.domain.statistics.entity;

import com.clover.recode.domain.problem.entity.Tag;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@DynamicInsert
public class AlgoReview {

    @Id
    private Long id;

    private Integer count;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

    @ColumnDefault("0")
    private Integer count1;

    @ColumnDefault("0")
    private Integer count2;

    @ColumnDefault("0")
    private Integer count3;

    @ColumnDefault("0")
    private Integer count4;

    @ColumnDefault("0")
    private Integer count5;

    @ColumnDefault("0")
    private Integer count6;

    @ColumnDefault("0")
    private Integer count7;

    @ColumnDefault("0")
    private Integer count8;



}
