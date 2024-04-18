package com.clover.recode.domain.statistics.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Statistics {

    @Id
    int id;

    int rank;

    int supplementaryNo;

    int randomNo;


}
