package com.clover.recode.domain.recode.service;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.entity.Problem;
import com.clover.recode.domain.problem.entity.Tag;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.recode.dto.*;
import com.clover.recode.domain.recode.dto.gpt.GptRequestDto;
import com.clover.recode.domain.recode.dto.gpt.GptResponseDto;
import com.clover.recode.domain.recode.dto.gpt.Message;
import com.clover.recode.domain.recode.dto.prompt.Prompt;
import com.clover.recode.domain.recode.dto.prompt.Prompt2;
import com.clover.recode.domain.recode.dto.prompt.PromptSub;
import com.clover.recode.domain.recode.entity.Recode;
import com.clover.recode.domain.recode.repository.RecodeRepository;
import com.clover.recode.domain.statistics.entity.AlgoReview;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.WeekReview;
import com.clover.recode.domain.statistics.repository.AlgoReviewRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.clover.recode.domain.statistics.repository.WeekReviewRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import static com.clover.recode.global.result.error.ErrorCode.*;

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

        String content = createRecode(code.getContent());

        Recode recode = Recode.builder()
                .code(code)
                .reviewTime(code.getCreatedTime().plusDays(1))
                .content(content)
                .build();

        recodeRepository.save(recode);

        LocalDate day;
        if(LocalDateTime.now().getHour() < 4) day= LocalDate.now().minusDays(1);
        else day= LocalDate.now();

        TodayProblem todayProblem = TodayProblem.builder()
            .isCompleted(false)
            .reviewCnt(0)
            .code(code)
            .title(code.getProblem().getTitle())
            .date(day)
            .problemNo(code.getProblem().getProblemNo())
            .user(code.getUser())
            .build();

        todayProblemRepository.save(todayProblem);

    }

    public String createRecode(String code) {

        List<Message> prompts = new ArrayList<>();
        prompts.add(new Message("system", Prompt2.systemPrompt));
        prompts.add(new Message("user", Prompt2.answerPrompt + code + "\n```"));

        // 시간 측정
        // long startTime = System.currentTimeMillis();

//        GptRequestDto request = new GptRequestDto("gpt-4o", prompts, 1, 1, 0, 0);
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

        // long elapsedTime = System.currentTimeMillis() - startTime;

        return content;

    }

    @Override
    public RecodeRes getRecode(Authentication authentication, Long codeId) {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        Code code = codeRepository.findByIdAndUserId(codeId, customUserDetails.getId())
                .orElseThrow(() -> new BusinessException(CODE_NOT_EXISTS));

        return getRecodeFromCode(code);
    }

    public RecodeRes getRecodeFromCode(Code code) {

        Recode recode = code.getRecode();

        if (recode == null)
            throw new BusinessException(RECODE_NOT_EXISTS);

        // 문제 정보 가져오기
        Problem problem = code.getProblem();

        // 난이도에 따른 레코드 생성하기, 로직처리 할때는 0부터 난이도 '하'로 처리
        int userDifficulty = code.getUser().getSetting().getDifficulty() - 1;

        String content = recode.getContent();
        List<String> notAnswers = new ArrayList<>();
        List<String> answers = new ArrayList<>();
        List<Integer> answerLevels = new ArrayList<>();
        List<Integer> answerLevels2 = new ArrayList<>();

        List<String>[] levelAnswers = new List[3];
        for (int i = 0; i < levelAnswers.length; i++)
            levelAnswers[i] = new ArrayList<>();

        // 코드 앞과 뒤에 생성된 ``` 제거
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

        StringBuilder notAnswer = new StringBuilder();
        mainLoop: for (int i = start; i < end; i++) {
            char ch = content.charAt(i);

            if (ch == '‽') {
                int blockDifficultyStart = 0;
                while (content.charAt(i + 1) == '‽') {
                    blockDifficultyStart++;
                    i++;
                }

                if (blockDifficultyStart <= userDifficulty) {
                    StringBuilder answer = new StringBuilder();
                    while (content.charAt(i + 1) != '▢') {
                        answer.append(content.charAt(i + 1));
                        i++;

                        if (i == end - 1 || content.charAt(i + 1) == '‽') {
                            notAnswer.append(answer);
                            continue mainLoop;
                        }
                    }

                    notAnswers.add(notAnswer.toString());
                    notAnswer = new StringBuilder();
                    String answerString = answer.toString();
                    answers.add(answerString);
                    answerLevels.add(blockDifficultyStart);
                    answerLevels2.add(levelAnswers[blockDifficultyStart].size());
                    levelAnswers[blockDifficultyStart].add(answerString);
                } else {
                    while (content.charAt(i + 1) != '▢') {
                        notAnswer.append(content.charAt(i + 1));
                        i++;

                        if (i == end - 1 || content.charAt(i + 1) == '‽')
                            continue mainLoop;
                    }
                }

                while (content.charAt(i + 1) == '▢')
                    i++;
            } else if (ch != '▢')
                notAnswer.append(ch);
        }
        notAnswers.add(notAnswer.toString());

        // 난이도 및 문제 길이 변 기본 빈칸 개수
        int contentLength = content.length();
        int baseNum = contentLength / 300 + 1;

        // 실제로 집어넣을 빈칸을 선정
        boolean[][] levelAnswersReal = new boolean[3][];
        for (int i = 0; i < 3; i++) {
            int levelAnswerSize = levelAnswers[i].size();
            levelAnswersReal[i] = new boolean[levelAnswerSize];
            if (levelAnswerSize > 0)
                for (int j = 0; j < baseNum; j++) {
                    if (j >= levelAnswerSize)
                        break;

                    int index = (int) (Math.random() * levelAnswerSize);
                    if (!levelAnswersReal[i][index])
                        levelAnswersReal[i][index] = true;
                    else
                        for (index = 0; index < levelAnswerSize; index++)
                            if (!levelAnswersReal[i][index]) {
                                levelAnswersReal[i][index] = true;
                                break;
                            }
                }
        }

        StringBuilder sb = new StringBuilder();

        List<String> realAnswers = new ArrayList<>();
        int answerSize = answers.size();
        for (int i = 0; i < answerSize; i++) {
            sb.append(notAnswers.get(i));

            if (levelAnswersReal[answerLevels.get(i)][answerLevels2.get(i)]) {
                // 선정된 빈칸

                String answer = answers.get(i);

                // 앞, 뒤에 공백 제거
                for (int j = 0; j < answer.length(); j++) {
                    if (answer.charAt(j) == ' ')
                        sb.append(' ');
                    else {
                        sb.append('‽').append('▢');
                        for (int k = answer.length() - 1; k >= 0; k--) {
                            if (answer.charAt(j) == ' ')
                                sb.append(' ');
                            else {
                                answer = answer.substring(j, k + 1);
                                break;
                            }
                        }
                        break;
                    }
                }

                // 빈 칸이 너무 길면 나누기
//                if (answer.length() > 10) {
//                    int div = answer.length() / 10 + 1;
//                    for (int j = 0; j < div; j++) {
//                        int step = answer.length() / div;
//                        int start2 = j * step;
//                        int end2 = (j + 1) * step;
//                        if (j < div - 1) {
//                            realAnswers.add(answer.substring(start2, end2 - 2));
//                            sb.append(answer, end2 - 2, end2).append('‽').append('▢');
//                        } else
//                            realAnswers.add(answer.substring(start2));
//                    }
//                } else
                    realAnswers.add(answer);
            } else
                sb.append(answers.get(i));
        }
        sb.append(notAnswers.getLast());

        // 태그 이름 가져오기
        List<String> tagNames = new ArrayList<>();
        List<Tag> tags = problem.getTags();
        for (Tag tag : tags) tagNames.add(tag.getName());

        // 반환 객체 생성 후 리턴
        return new RecodeRes(ProblemDto.builder()
                .problemNo(problem.getProblemNo())
                .title(problem.getTitle())
                .level(problem.getLevel())
                .content(problem.getContent())
                .tags(tagNames)
                .build()
                , code.getContent()
                , sb.toString()
                , realAnswers);
    }

    @Override
    @Transactional
    public void recodeSubmit(Long codeId) {
        Code code = codeRepository.findById(codeId)
                .orElseThrow(() -> new BusinessException(CODE_NOT_EXISTS));

        Recode recode = code.getRecode();

        if (recode == null)
            throw new BusinessException(RECODE_NOT_EXISTS);

        LocalDateTime submitTime = LocalDateTime.now();

        recode.setSubmitTime(submitTime);

        int submitCount = recode.getSubmitCount() + 1;
        recode.setSubmitCount(submitCount);

        int addDays = 0;
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
                break;
        }

        recode.setReviewTime(submitTime.plusDays(addDays));

        recodeRepository.save(recode);
        codeRepository.save(code);

        //문제를 풀고 난 후, 통계 업데이트 해주기

        //오늘의 복습문제 is_complete true로 변경
        //새벽4시 이전이면 이전날
        //새벽4시 이후면 Today
        //code_id는 unique가 아니기 때문에, findByCodeId를 하면 값이
        //여러개가 리턴될 수 있음
        //code_id + 날짜로 가져오기로 변경

        LocalDate day;
        if(LocalDateTime.now().getHour() < 4) day= LocalDate.now().minusDays(1);
        else day= LocalDate.now();
       todayProblemRepository.findByCodeIdAndDate(codeId, day).ifPresent(todayProblem -> {

           todayProblem.setCompleted(true);

           todayProblemRepository.save(todayProblem);

        });

        //매주 복습량 테이블 오늘 날짜 복습량 +1 변경
        Long statisticsId= code.getUser().getStatistics().getId();
        weekReviewRepository.findByIdAndDateToday(statisticsId, day).ifPresentOrElse(weekReview -> {

            int count= weekReview.getCount();
            count++;
            weekReview.setCount(count);

        }, ()->{

        WeekReview weekReview= WeekReview.builder()
                .statistics(code.getUser().getStatistics())
                .date(day)
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
            String tagName = tag.getName();

            if("수학".equals(tagName)) {
                algoReview.setMathCnt(algoReview.getMathCnt()+1);
            }
            if("구현".equals(tagName)) {
                algoReview.setImplementationCnt(algoReview.getImplementationCnt()+1);
            }
            if("그리디 알고리즘".equals(tagName)) {
                algoReview.setGreedyCnt(algoReview.getGreedyCnt()+1);
            }
            if("문자열".equals(tagName)) {
                algoReview.setStringCnt(algoReview.getStringCnt()+1);
            }
            if("자료 구조".equals(tagName)) {
                algoReview.setData_structuresCnt(algoReview.getData_structuresCnt()+1);
            }
            if("그래프 이론".equals(tagName)) {
                algoReview.setGraphsCnt(algoReview.getGraphsCnt()+1);
            }
            if("다이나믹 프로그래밍".equals(tagName)) {
                algoReview.setDpCnt(algoReview.getDpCnt()+1);
            }
            if("기하학".equals(tagName)) {
                algoReview.setGeometryCnt(algoReview.getGeometryCnt()+1);
            }

            algoReviewRepository.save(algoReview);

        }
    }

}
