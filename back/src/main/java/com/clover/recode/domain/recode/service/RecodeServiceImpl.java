package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.problem.dto.ProblemDto;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.entity.Tag;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.recode.dto.*;
import com.clover.recode.domain.recode.entity.Recode;
import com.clover.recode.domain.recode.repository.RecodeRepository;
import com.clover.recode.domain.statistics.entity.AlgoReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.WeekReview;
import com.clover.recode.domain.statistics.repository.AlgoReviewRepository;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.clover.recode.domain.statistics.repository.WeekReviewRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final TodayProblemRepository todayProblemRepository;
    private final WeekReviewRepository weekReviewRepository;
    private final AlgoReviewRepository algoReviewRepository;

    @Override
    @Transactional
    @Async
    public void saveRecode(Code code) {

        List<Message> prompts = new ArrayList<>();
        prompts.add(new Message("system", EnglishPrompt.systemPrompt));
        prompts.add(new Message("user", EnglishPrompt.answerPrompt + code.getContent() + "\n```"));

        GptRequestDto request = new GptRequestDto("gpt-4-turbo", prompts, 1, 1, 0, 0);

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
        int start = 0;
        int end = length - 1;
        if (length > 4 && content.startsWith("```\n")) {
            start = 4;

            if (content.endsWith("\n```"))
                end = length - 4;
            else
                log.error("이상한 recode : {}", content);
        }

        mainLoop: for (int i = start; i < end; i++) {
            char ch = content.charAt(i);

            if (ch == '‽') {
                int blockDifficultyStart = 1;
                while (content.charAt(i + 1) == '‽') {
                    blockDifficultyStart++;
                    i++;
                }

                if (blockDifficultyStart == userDifficulty) {
                    StringBuilder answer = new StringBuilder();
                    while (content.charAt(i + 1) != '▢') {
                        answer.append(content.charAt(i + 1));
                        i++;

                        if (i == end - 1 || content.charAt(i + 1) == '‽') {
                            sb.append(answer);
                            continue mainLoop;
                        }
                    }

                    sb.append("‽").append("▢");
                    answers.add(answer.toString());
                } else {
                    while (content.charAt(i + 1) != '▢') {
                        sb.append(content.charAt(i + 1));
                        i++;

                        if (i == end - 1 || content.charAt(i + 1) == '‽')
                            continue mainLoop;
                    }
                }

                while (content.charAt(i + 1) == '▢')
                    i++;
            } else if (ch != '▢')
                sb.append(ch);
        }

        List<String> tagNames = new ArrayList<>();
        List<Tag> tags = problem.getTags();
        for (Tag tag : tags) tagNames.add(tag.getName());

        return new RecodeRes(ProblemDto.builder()
                .problemNo(problem.getProblemNo())
                .title(problem.getTitle())
                .level(problem.getLevel())
                .content(problem.getContent())
                .tags(tagNames)
                .build()
                , sb.toString()
                , answers);
    }

    @Override
    @Transactional
    public void recodeSubmit(Long codeId) {
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

        //문제를 풀고 난 후, 통계 업데이트 해주기

        //오늘의 복습문제 is_complete true로 변경
       todayProblemRepository.findById(codeId).ifPresent(todayProblem -> {

           todayProblem.setCompleted(true);

           todayProblemRepository.save(todayProblem);

        });

        //매주 복습량 테이블 오늘 날짜 복습량 +1 변경
        Long statisticsId= code.getUser().getStatistics().getId();
        weekReviewRepository.findByIdAndDateToday(statisticsId).ifPresentOrElse(weekReview -> {

            int count= weekReview.getCount();
            count++;
            weekReview.setCount(count);

        }, ()->{

        WeekReview weekReview= WeekReview.builder()
                .statistics(code.getUser().getStatistics())
                .date(LocalDate.now())
                .count(1)
                .build();


        int sqn= weekReview.getStatistics().getSequence();
        sqn++;
        weekReview.getStatistics().setSequence(sqn);

        weekReviewRepository.save(weekReview);

        });




        //알고리즘 별 복습한 문제 +1 변경
        List<Tag> tags= code.getProblem().getTags();
        for(Tag tag: tags)
            log.info(tag.getId().toString());

        AlgoReview algoReview= algoReviewRepository.findById(statisticsId).orElseThrow();

        for(Tag tag: tags){

            log.info("tag..getId(): "+ tag.getId());
            switch (tag.getId()){
                case 1: algoReview.setMathCnt(algoReview.getMathCnt()+1); break;
                case 2: algoReview.setImplementationCnt(algoReview.getImplementationCnt()+1); break;
                case 3: algoReview.setGreedyCnt(algoReview.getGreedyCnt()+1); break;
                case 4: algoReview.setStringCnt(algoReview.getStringCnt()+1); break;
                case 5: algoReview.setData_structuresCnt(algoReview.getData_structuresCnt()+1); break;
                case 6: algoReview.setGraphsCnt(algoReview.getGraphsCnt()+1); break;
                case 7: algoReview.setDpCnt(algoReview.getDpCnt()+1); break;
                case 8: algoReview.setGeometryCnt(algoReview.getGeometryCnt()+1); break;

            }

            algoReviewRepository.save(algoReview);

        }
    }

}
