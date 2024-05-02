package com.clover.recode.domain.problem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeDTO {

    private Long codeNo;
    private String name;
    private Boolean reviewStatus;
    private Integer submitCount; // Recode에서 가져오는 제출 횟수 추가

}
