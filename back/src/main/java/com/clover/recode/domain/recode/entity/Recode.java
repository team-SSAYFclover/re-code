package com.clover.recode.domain.recode.entity;

import com.clover.recode.domain.statistics.entity.Statistics;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Recode {

    @Id
    private Long id;

    @NotNull
    private LocalDate review_time;

    private LocalDate submit_time;

    @NotNull
    @ColumnDefault("0")
    private int submit_count;

    @NotNull
    private String content;

}

