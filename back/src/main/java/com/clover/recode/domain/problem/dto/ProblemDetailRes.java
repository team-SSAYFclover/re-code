package com.clover.recode.domain.problem.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor
public class ProblemDetailRes {

    private Integer problemNo;      // 문제 번호 (백준 문제 번호)
    private String title;           // 문제 제목 (달이 차오른다, 가자...)
    private Integer level;          // 문제 등급 (브론즈/실버/골드...)
    private Integer reviewCount;    // 복습량
    private List<String> tags;      // 알고리즘 분류 태그 리스트 (BFS, DFS, DP ...)
    private String content;
    private List<CodeResList> codeResLists;

}
