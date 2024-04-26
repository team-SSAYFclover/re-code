package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.recode.dto.GptRequestDto;
import com.clover.recode.domain.recode.dto.GptResponseDto;
import com.clover.recode.domain.recode.dto.Message;
import com.clover.recode.global.config.ChatGPTConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RecodeServiceImpl implements RecodeService {

    private final RestTemplate restTemplate;

    @Value("${spring.openai.model}")
    private String model;

    @Override
    public String getRecode(String code) {

        List<Message> prompts = List.of(new Message("user", code));
        GptRequestDto request = new GptRequestDto(model, prompts, 1, 256, 1, 0, 0);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GptRequestDto> entity = new HttpEntity<>(request, headers);

        // OpenAI server로 restTemplate을 통해 request를 보내고 response를 받는다.
        GptResponseDto gptResponse = restTemplate.exchange(
                "https://api.openai.com/v1/chat/completions", HttpMethod.POST, entity, GptResponseDto.class).getBody();
        if (gptResponse != null) {
            return "gptResponse";
        } else {
            throw new RuntimeException("Error parsing response from OpenAI Server");
        }

    }
}
