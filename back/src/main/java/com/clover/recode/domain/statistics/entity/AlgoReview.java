package com.clover.recode.domain.statistics.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.Map;

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


    public String getMinFieldName(Map<String, Integer> fields) {
        String minFieldName = null;
        int minValue = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : fields.entrySet()) {
            String fieldName = entry.getKey();
            int value = entry.getValue();

            if (value < minValue) {
                minFieldName = fieldName;
                minValue = value;
            }
        }

        return minFieldName;
    }



}
