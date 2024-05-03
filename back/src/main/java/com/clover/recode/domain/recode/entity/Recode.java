package com.clover.recode.domain.recode.entity;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.user.entity.User;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id")
    private Code code;

    @NotNull
    private LocalDate review_time;

    private LocalDate submit_time;

    @NotNull
    @ColumnDefault("0")
    private int submit_count;

    @NotNull
    private String content;


}

