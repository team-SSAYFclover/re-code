package com.clover.recode.domain.problem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {
    private Long id;                // 문제 ID  (DB내 문제 번호)
    private Integer problemNo;      // 문제 번호 (백준 문제 번호)
    private String title;           // 문제 제목 (달이 차오른다, 가자...)
    private Integer level;          // 문제 등급 (브론즈/실버/골드...)
    private String content;         // 문제 내용
    private Integer reviewCount;    // 복습량(해당 유저의 제출회수 합)
    private List<String> tags;      // 알고리즘 분류 태그 리스트 (BFS, DFS, DP ...)
}
