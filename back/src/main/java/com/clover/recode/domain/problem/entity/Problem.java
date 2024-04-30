package com.clover.recode.domain.problem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Problem Entity

// Problem Entity
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "problem")
public class Problem {
    @Id
    private Long id;
    private Integer problemNo;
    private String title;
    private Integer level;
    @Column(columnDefinition = "TEXT")
    private String content;
}
