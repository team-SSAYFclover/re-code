package com.clover.recode.domain.recode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GptResponseDto {

    private List<Choice> choices;
    private String id;
    private String model;
    private String object;
}