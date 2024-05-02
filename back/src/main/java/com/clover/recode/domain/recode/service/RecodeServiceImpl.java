package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.dto.ProblemCodeDto;
import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.recode.dto.GptRequestDto;
import com.clover.recode.domain.recode.dto.GptResponseDto;
import com.clover.recode.domain.recode.dto.Message;
import com.clover.recode.domain.recode.dto.RecodeRes;
import com.clover.recode.domain.recode.entity.Recode;
import com.clover.recode.domain.recode.repository.RecodeRepository;
import com.clover.recode.domain.user.entity.Setting;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.global.result.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
    public void saveRecode(ProblemCodeDto problemDto) {

        String code = "";

        List<Message> prompts = List.of(new Message("user", code));
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

        String recode = gptResponse.getChoices().getFirst().getMessage().getContent();

        // TODO: 기본 레코드 저장하기
    }

    @Override
    public RecodeRes getRecode(Long codeId) {

        Recode recode = recodeRepository.findById(codeId)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS));

        // TODO: 문제 정보 가져오기
        Problem problem = codeRepository.findById(codeId)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS)).getProblem();

        // TODO: 난이도에 따른 레코드 생성하기
        Setting setting = codeRepository.findById(codeId)
                .orElseThrow(() -> new BusinessException(USER_NOT_EXISTS)).getProblem();
        return null;
    }

    @Override
    public void addRecodeCount(int codeId) {
        recodeRepository.incrementSubmitCount(codeId);
        // TODO: 업데이트 한 값 불러온 뒤 활성화 시간 갱신
    }

}
