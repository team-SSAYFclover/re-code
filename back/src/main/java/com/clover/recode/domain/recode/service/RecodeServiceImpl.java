package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.recode.dto.*;
import com.clover.recode.domain.recode.entity.Recode;
import com.clover.recode.domain.recode.repository.RecodeRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.clover.recode.global.result.error.ErrorCode.USER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RecodeServiceImpl implements RecodeService {

    private final RestTemplate restTemplate;
    private final RecodeRepository recodeRepository;
    private final CodeRepository codeRepository;

    @Value("${spring.openai.model}")
    private String model;

    @Override
    public void saveRecode(Code code) {

        List<Message> prompts = List.of(new Message("user", EnglishPrompt.prompt + "```\n" + code.getContent() + "\n```"));
        GptRequestDto request = new GptRequestDto(model, prompts, 1, 256, 1, 0, 0);

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GptRequestDto> entity = new HttpEntity<>(request, headers);

        // OpenAI server로 restTemplate을 통해 request를 보내고 response를 받는다.
        GptResponseDto gptResponse = restTemplate.exchange(
                "https://api.openai.com/v1/chat/completions", HttpMethod.POST, entity, GptResponseDto.class).getBody();
        if (gptResponse == null)
            throw new RuntimeException("Error parsing response from OpenAI Server");

        String content = gptResponse.getChoices().getFirst().getMessage().getContent();

        Recode recode = Recode.builder()
                .code(code)
                .reviewTime(code.getCreatedTime().plusDays(1))
                .content(content)
                .build();

        recodeRepository.save(recode);

    }

    @Override
    public RecodeRes getRecode(Long codeId) {

        Code code = codeRepository.findById(codeId)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

        Recode recode = code.getRecode();

        // 문제 정보 가져오기
        Problem problem = code.getProblem();

        // 난이도에 따른 레코드 생성하기
        int userDifficulty = code.getUser().getSetting().getDifficulty();

        String content = recode.getContent();
        StringBuilder sb = new StringBuilder();
        List<String> answers = new ArrayList<>();
        int length = content.length();
        for (int i = 0; i < length; i++) {
            char ch = content.charAt(i);

            if (ch == '‽') {
                int blockDifficulty = 1;
                while (content.charAt(i + 1) == '‽') {
                    blockDifficulty++;
                    i++;
                }

                if (blockDifficulty == userDifficulty) {
                    sb.append('‽' * userDifficulty).append('▢' * userDifficulty);

                    StringBuilder answer = new StringBuilder();
                    while (content.charAt(i + 1) != '▢') {
                        answer.append(content.charAt(i + 1));
                        i++;
                    }

                    answers.add(answer.toString());
                } else {
                    while (content.charAt(i + 1) != '▢') {
                        sb.append(content.charAt(i + 1));
                        i++;
                    }
                }

                i += blockDifficulty;
            } else
                sb.append(ch);
        }

        return new RecodeRes(problem, sb.toString(), answers);
    }

    @Override
    public void addRecodeCount(Long codeId) {
        Code code = codeRepository.findById(codeId)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

        Recode recode = code.getRecode();

        LocalDateTime submitTime = LocalDateTime.now();

        recode.setSubmitTime(submitTime);

        int submitCount = recode.getSubmitCount() + 1;
        recode.setSubmitCount(submitCount);

        int addDays;
        switch (submitCount) {
            case 1:
                addDays = 3;
                break;
            case 2:
                addDays = 7;
                break;
            case 3:
                addDays = 30;
                break;
            default:
                code.setReviewStatus(false);
                return;
        }

        recode.setReviewTime(submitTime.plusDays(addDays));

        recodeRepository.save(recode);

    }

}
