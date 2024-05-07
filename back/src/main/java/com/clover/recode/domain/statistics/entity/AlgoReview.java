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
    private Long statisticsId;

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;


    @ColumnDefault("0")
    private Integer mathCnt;

    @ColumnDefault("0")
    private Integer implementationCnt;

    @ColumnDefault("0")
    private Integer greedyCnt;

    @ColumnDefault("0")
    private Integer stringCnt;

    @ColumnDefault("0")
    private Integer data_structuresCnt;

    @ColumnDefault("0")
    private Integer graphsCnt;

    @ColumnDefault("0")
    private Integer dpCnt;

    @ColumnDefault("0")
    private Integer geometryCnt;



}
