package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.WeekReviewsRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final WeekReviewsRepository weekReviewsRepository;

    @Autowired
    public StatisticsServiceImpl(@Qualifier("weekReviewsRepositoryImpl") WeekReviewsRepository weekReviewsRepository,
                                 @Qualifier("statisticsRepository") StatisticsRepository statisticsRepository) {
        this.weekReviewsRepository = weekReviewsRepository;
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public StatisticsListRes getStatisticsList(int userId) {
        Statistics statistics = statisticsRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(USER_NOT_FOUND));

            LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            List<Integer> weekReviewsList= weekReviewsRepository.findReviewsBetweenDates(startOfWeek, endOfWeek);

        // Response 객체를 생성하고 필드를 채웁니다. 'todayReviews'와 같은 필드는 추가 정보가 필요합니다.
        StatisticsListRes response = new StatisticsListRes();
        response.setSequence(statistics.getSequence()); // 또는 다른 적절한 필드
        response.setRanking(statistics.getRanking());
        response.setWeekReviews(weekReviewsList);
        response.setSupplementaryQuestion(statistics.getSupplementaryNo());
        response.setRandomQuestion(statistics.getRandomNo());

        return response;
    }
}
