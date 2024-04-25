package com.clover.recode.problem.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {

    private Long id;
    private Integer problemNo;
    private String title;
    private Integer level;
    private Integer reviewCount;
    private List<String> tags;
}
