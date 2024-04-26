package com.clover.recode.domain.recode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {

    private String role;
    private String content;
}