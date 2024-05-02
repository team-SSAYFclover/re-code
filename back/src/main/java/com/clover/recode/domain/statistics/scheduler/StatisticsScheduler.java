package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeCustomRepository;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.clover.recode.domain.statistics.repository.TodayReviewRepository;
import com.clover.recode.domain.user.entity.User;
import com.clover.recode.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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



//        for (User user : users) {
//
//            log.info("todayReview 추가완료");
//
//            List<Code> codesToReview = codeCustomRepository.findByReviewStatusFalseAndReviewTimeBefore(user.getId(), today);
//
//            log.info("codesToReview 가져오기 성공");
//
//            for(Code code: codesToReview){
//                System.out.println(code.getCodeNo());
//            }
//
//            List<TodayProblem> todayProblems = new ArrayList<>();
//            for (Code code : codesToReview) {
//                TodayProblem todayProblem = TodayProblem.builder()
//                        .isCompleted(false)
//                        .reviewCnt(code.getRecode().getSubmit_count())
//                        .todayReview(todayReview)
//                        .code(code)
//                        .name(code.getProblem().getTitle())
//                        .build();
//                todayProblems.add(todayProblem);
//            }
//
//            todayReviewRepository.save(todayReview);
//            todayProblemRepository.saveAll(todayProblems);
//
//        }
    }
}
