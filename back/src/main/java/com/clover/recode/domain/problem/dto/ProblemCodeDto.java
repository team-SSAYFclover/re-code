package com.clover.recode.domain.problem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProblemCodeDto {
    private Long userId;
    private Long problemId;     // 문제 ID  (DB내 문제 번호)
    private Integer problemNo;  // 문제 번호 (백준 문제 번호)
    private String title;       // 문제 제목 (달이 차오른다, 가자...)
    private Integer level;      // 문제 등급 (브론즈/실버/골드...)
    private String content;     // 문제 내용
    private List<String> tags;      // 알고리즘 분류 태그 리스트 (BFS, DFS, DP ...)
    private Long codeNo;
    private Integer reviewCount;    // 복습량 (해당 유저의 제출회수 합)
    private String codeContent;  // 코드 내용
}
