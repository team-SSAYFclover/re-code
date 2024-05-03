package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeCustomRepository;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
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

    private final CodeCustomRepository codeCustomRepository;
    private final TodayProblemRepository todayProblemRepository;

    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void updateTodayProblem() {

            LocalDate today = LocalDate.now();
            List<Code> codesToReview = codeCustomRepository.findByReviewStatusFalseAndReviewTimeBefore(today);

            for(Code code: codesToReview) {
                    TodayProblem todayProblem = TodayProblem.builder()
                            .isCompleted(false)
                            .reviewCnt(code.getRecode().getSubmitCount())
                            .code(code)
                            .name(code.getProblem().getTitle())
                            .date(today)
                            .build();

                todayProblemRepository.save(todayProblem);
            }

    }
}
