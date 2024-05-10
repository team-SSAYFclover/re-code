package com.clover.recode.domain.problem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ProblemRes {

    private Integer problemNo;      // 문제 번호 (백준 문제 번호)
    private String title;           // 문제 제목 (달이 차오른다, 가자...)
    private Integer level;          // 문제 등급 (브론즈/실버/골드...)
    private String content;         // 문제 내용
    private Integer reviewCount;    // 복습량
    private List<String> tags;      // 알고리즘 분류 태그 리스트 (BFS, DFS, DP ...)
}
