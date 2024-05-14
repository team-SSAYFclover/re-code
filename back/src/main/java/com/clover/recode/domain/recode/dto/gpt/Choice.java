package com.clover.recode.domain.recode.dto.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Choice {

    private int index;
    private Message message;
}
