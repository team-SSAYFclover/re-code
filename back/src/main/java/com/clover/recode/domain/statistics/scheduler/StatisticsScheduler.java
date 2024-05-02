package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.dto.CodeDTO;
import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeCustomRepository;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.TodayReview;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.clover.recode.domain.statistics.repository.TodayReviewRepository;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticsScheduler {

    private final UserRepository userRepository;
    private final TodayReviewRepository todayReviewRepository;
    private final CodeCustomRepository codeCustomRepository;
    private final TodayProblemRepository todayProblemRepository;

    @Scheduled(cron = "0 05 16 * * *")
    @Transactional
    public void updateTodayProblem() {

        List<User> users = userRepository.findAll();

        LocalDate today = LocalDate.now();

        for (User user : users) {
            TodayReview todayReview = new TodayReview();
            todayReview.setDate(today);
            todayReview.setUser(user);

            log.info("todayReview 추가완료");
            log.info(String.valueOf(todayReview.getId()));
            log.info(String.valueOf(todayReview.getDate()));
            log.info(String.valueOf(todayReview.getUser().getId()));

            List<Code> codesToReview = codeCustomRepository.findByReviewStatusFalseAndReviewTimeBefore(user.getId(), today);

            log.info("codesToReview 가져오기 성공");

            for(Code code: codesToReview){
                System.out.println(code.getCodeNo());
            }

            List<TodayProblem> todayProblems = new ArrayList<>();
            for (Code code : codesToReview) {
                TodayProblem todayProblem = TodayProblem.builder()
                        .isCompleted(false)
                        .reviewCnt(code.getRecode().getSubmit_count())
                        .todayReview(todayReview)
                        .code(code)
                        .name(code.getProblem().getTitle())
                        .build();
                todayProblems.add(todayProblem);
            }

            todayReviewRepository.save(todayReview);
            todayProblemRepository.saveAll(todayProblems);

        }
    }
}
