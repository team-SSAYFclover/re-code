package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.entity.TodayProblem;
import com.clover.recode.domain.statistics.entity.TodayReview;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static com.clover.recode.global.result.error.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;


    @Override
    @Transactional
    public StatisticsListRes getStatisticsList(int userId) {
        Statistics statistics = statisticsRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(USER_NOT_FOUND));

            LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            List<Integer> weekReviewsList= statisticsRepository.findReviewsBetweenDates(startOfWeek, endOfWeek);

        log.info("weekReviewsList 가져옴");
            List<TodayProblem> todayProblemList = statisticsRepository.findTodayReviews(LocalDate.now(), statistics.getId());

        log.info("todayReviewList 가져옴");

        StatisticsListRes response = new StatisticsListRes();
        response.setSequence(statistics.getSequence()); // 또는 다른 적절한 필드
        response.setRanking(statistics.getRanking());
        response.setWeekReviews(weekReviewsList);
        response.setSupplementaryQuestion(statistics.getSupplementaryNo());
        response.setRandomQuestion(statistics.getRandomNo());
        response.setTodayProblems(todayProblemList);

        log.info("리턴직전");
        return response;
    }
}
