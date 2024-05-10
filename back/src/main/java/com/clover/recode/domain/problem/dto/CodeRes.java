package com.clover.recode.domain.problem.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@AllArgsConstructor
public class CodeRes {
    private Long id;
    private String name;    // 코드 제목
    private String content; // 코드 내용
    private LocalDateTime createdTime; // 생성 시간
}
