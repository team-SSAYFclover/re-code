package com.clover.recode.domain.problem.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class ProblemRegistReq {
    private Integer problemNo;      // 문제 번호 (백준 문제 번호)
    private String title;           // 문제 제목 (달이 차오른다, 가자...)
    private Integer level;          // 문제 등급 (브론즈/실버/골드...)
    private String content;         // 문제 내용
    private List<String> tags;      // 알고리즘 분류 태그 리스트 (BFS, DFS, DP ...)

}
