package com.clover.recode.domain.recode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GptRequestDto {

    private String model;           // 모델명
    private List<Message> messages; // 질문들
    private float temperature;      // 답변 다양성
    private int max_tokens;         // 답변 최대 길이
    private int top_p;              // 답변 다양성
    private int frequency_penalty;  // 답변 중복 방지
    private int presence_penalty;   // 답변 중복 방지
}
