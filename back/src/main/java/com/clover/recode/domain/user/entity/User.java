package com.clover.recode.domain.user.entity;

import com.clover.recode.domain.statistics.entity.Statistics;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;
}

