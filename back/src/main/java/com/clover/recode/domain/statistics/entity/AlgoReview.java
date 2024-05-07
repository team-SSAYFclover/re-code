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


    private int mathCnt;

    private int implementationCnt;

    private int greedyCnt;

    private int stringCnt;

    private int data_structuresCnt;

    private int graphsCnt;

    private int dpCnt;

    private int geometryCnt;



}
