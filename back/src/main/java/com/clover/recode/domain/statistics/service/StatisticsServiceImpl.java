package com.clover.recode.domain.statistics.service;

import com.clover.recode.domain.auth.dto.CustomOAuth2User;
import com.clover.recode.domain.statistics.dto.AlgoReviewDto;
import com.clover.recode.domain.statistics.dto.TodayProblemDto;
import com.clover.recode.domain.statistics.dto.WeekReviewDto;
import com.clover.recode.domain.statistics.dto.response.StatisticsListRes;
import com.clover.recode.domain.statistics.entity.AlgoReview;
import com.clover.recode.domain.statistics.entity.Statistics;
import com.clover.recode.domain.statistics.repository.AlgoReviewRepository;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.clover.recode.domain.statistics.repository.WeekReviewRepository;
import com.clover.recode.global.result.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.clover.recode.global.result.error.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final WeekReviewRepository weekReviewRepository;
    private final AlgoReviewRepository algoReviewRepository;
    private final TodayProblemRepository todayProblemRepository;


    @Override
    @Transactional
    public StatisticsListRes getStatisticsList(Authentication authentication) {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        Statistics statistics = statisticsRepository.findById(customUserDetails.getId())
                .orElseThrow(()-> new BusinessException(USER_NOT_FOUND));

            LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<Integer> weekReviewList= weekReviewRepository.findReviewsBetweenDates(startOfWeek, endOfWeek, statistics.getId());

        WeekReviewDto weekReviewDto= WeekReviewDto.builder()
                .mon(weekReviewList.get(0))
                .tue(weekReviewList.get(1))
                .wed(weekReviewList.get(2))
                .thu(weekReviewList.get(3))
                .fri(weekReviewList.get(4))
                .sat(weekReviewList.get(5))
                .sun(weekReviewList.get(6))
                .build();

            List<TodayProblemDto> todayProblem = todayProblemRepository.findByUserId(customUserDetails.getId());

            // 문제 제목별로 카운팅하기 위한 맵
            Map<String, Integer> titleCount = new HashMap<>();

                todayProblem.forEach(dto -> {
                String originalTitle = dto.getTitle();
                int count = titleCount.getOrDefault(originalTitle, 0) + 1;
                titleCount.put(originalTitle, count); // 제목별로 카운트 증가

                // 첫 번째 아이템이 아니라면 제목 뒤에 (count)를 붙임
                if (count > 1) {
                    String newTitle = originalTitle + "(" + count + ")";
                    dto.setTitle(newTitle);
                }
            });


        AlgoReview algoReview = algoReviewRepository.findById(statistics.getId()).orElseThrow();
        AlgoReviewDto algoReviewDto= AlgoReviewDto.builder()
                .math(algoReview.getMathCnt())
                .implementation(algoReview.getImplementationCnt())
                .greedy(algoReview.getGreedyCnt())
                .string(algoReview.getStringCnt())
                .data_structures(algoReview.getData_structuresCnt())
                .graphs(algoReview.getGraphsCnt())
                .dp(algoReview.getDpCnt())
                .geometry(algoReview.getGeometryCnt())
                .build();


        StatisticsListRes response = StatisticsListRes.builder()
                .sequence(statistics.getSequence())
                .ranking(statistics.getRanking())
                .weekReviews(weekReviewDto)
                .supplementaryQuestion(statistics.getSupplementaryNo())
                .randomQuestion(statistics.getRandomNo())
                .algoReview(algoReviewDto)
                .todayProblems(todayProblem)
                .build();

        return response;
    }

    @Override
    public Integer getReviewCnt(Authentication authentication) {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();


        Statistics statistics = statisticsRepository.findById(customUserDetails.getId())
                .orElseThrow(()-> new BusinessException(USER_NOT_FOUND));

        return weekReviewRepository.countByTodayReview(statistics.getId(), LocalDate.now());


    }

    @Override
    public List<TodayProblemDto> getReviews(Authentication authentication) {

        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        return null;
        //return todayReviewCustomRepository.findTodayReviews(userId, LocalDate.now());

    }
}
