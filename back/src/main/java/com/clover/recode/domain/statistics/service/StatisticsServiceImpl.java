package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import com.clover.recode.domain.statistics.dto.response.TodayProblemRes;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayReviewCustomRepository;
import com.clover.recode.domain.user.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final StatisticsRepository statisticsRepository;
    private final TodayReviewCustomRepository todayReviewCustomRepository;


    @Override
    @Transactional
    public StatisticsListRes getStatisticsList(Long userId) {
        Statistics statistics = statisticsRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(USER_NOT_FOUND));

            LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            List<Integer> weekReviewList= statisticsRepository.findReviewsBetweenDates(startOfWeek, endOfWeek, statistics.getId());

            List<TodayProblemRes> todayProblemList = todayReviewCustomRepository.findTodayReviews(userId, LocalDate.now());

            List<Integer> algoReviewList = statisticsRepository.findAlgoReviewList(statistics.getId());

        StatisticsListRes response = new StatisticsListRes();
        response.setSequence(statistics.getSequence());
        response.setRanking(statistics.getRanking());
        response.setWeekReviews(weekReviewList);
        response.setSupplementaryQuestion(statistics.getSupplementaryNo());
        response.setRandomQuestion(statistics.getRandomNo());
        response.setTodayProblems(todayProblemList);
        response.setAlgoReview(algoReviewList);

        return response;
    }

    @Override
    public Integer getReviewCnt(Long userId) {

        Statistics statistics = statisticsRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(USER_NOT_FOUND));

        return statisticsRepository.countByTodayWeview(statistics.getId(), LocalDate.now());


    }

    @Override
    public List<TodayProblemRes> getReviews(Long userId) {

        return todayReviewCustomRepository.findTodayReviews(userId, LocalDate.now());

    }
}
