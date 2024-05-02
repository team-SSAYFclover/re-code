package com.clover.recode.domain.recode.entity;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder(toBuilder = true)
public class Recode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "code_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Code code;

    @Column(nullable = false)
    private LocalDateTime reviewTime;

    @Column()
    private LocalDateTime submitTime;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int submitCount;

    @Column(nullable = false)
    private String content;

}