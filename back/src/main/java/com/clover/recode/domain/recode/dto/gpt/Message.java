package com.clover.recode.domain.recode.dto.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {

    private String role;
    private String content;
}