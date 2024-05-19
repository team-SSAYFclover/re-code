package com.clover.recode.domain.statistics.scheduler;

import com.clover.recode.domain.problem.entity.Code;
import com.clover.recode.domain.problem.repository.CodeRepository;
import com.clover.recode.domain.statistics.entity.*;
import com.clover.recode.domain.statistics.repository.StatisticsRepository;
import com.clover.recode.domain.statistics.repository.TodayProblemRepository;
import com.clover.recode.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsScheduler {

    private final JdbcTemplate jdbcTemplate;
    private final CodeRepository codeRepository;
    private final StatisticsRepository statisticsRepository;
    private final JPAQueryFactory jpaQueryFactory;


    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void updateTodayProblem() {

            //오늘의 문제를 업데이트한다
            LocalDate today = LocalDate.now();

            List<Code> codesToReview = codeRepository.findByReviewStatusTrueAndReviewTimeBefore();
            Map<Long, Map<String, Integer>> userTitleCountMap = new HashMap<>();

            List<TodayProblem> todayProblemList = new ArrayList<>();
            for(Code code: codesToReview) {

                Long userId = code.getUser().getId();
                String originalTitle = code.getProblem().getTitle();
                String title = originalTitle;

                // 사용자를 위한 내부 맵이 없으면 초기화
                userTitleCountMap.putIfAbsent(userId, new HashMap<>());
                Map<String, Integer> titleCountMap = userTitleCountMap.get(userId);

                if (titleCountMap.containsKey(originalTitle)) {
                    int count = titleCountMap.get(originalTitle) + 1;
                    titleCountMap.put(originalTitle, count);
                    title = originalTitle + "(" + count + ")";
                } else {
                    titleCountMap.put(originalTitle, 1);
                }

                    TodayProblem todayProblem = TodayProblem.builder()
                            .isCompleted(false)
                            .reviewCnt(code.getRecode().getSubmitCount())
                            .code(code)
                            .title(title)
                            .date(today)
                            .problemNo(code.getProblem().getProblemNo())
                            .user(code.getUser())
                            .build();

                    todayProblemList.add(todayProblem);
            }

            //JPA에선 Id가 자동인 경우 배치를 쓸 수없다
        //따라서 jdbcTemplate를 이용함

        String sql = "INSERT INTO today_problem " +
                "(date, is_completed, problem_no, review_cnt, title, code_id, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                TodayProblem todayProblem = todayProblemList.get(i);
                ps.setDate(1, java.sql.Date.valueOf(today));
                ps.setBoolean(2, todayProblem.isCompleted());
                ps.setInt(3, todayProblem.getProblemNo());
                ps.setInt(4, todayProblem.getReviewCnt()+1);
                ps.setString(5, todayProblem.getTitle());
                ps.setLong(6, todayProblem.getCode().getId());
                ps.setLong(7, todayProblem.getUser().getId());
            }

            @Override
            public int getBatchSize() {
                return todayProblemList.size();
            }
        });
    }

    @Scheduled(cron = "0 0 4 * * *")
    @Transactional
    public void updateRanking() {

        QStatistics statistics= QStatistics.statistics;
        QWeekReview weekReview= QWeekReview.weekReview;

        //내 위치 백분율로 찾기
        //통계, 매주 복습량 테이블에서 전체 복습량 갯수에서
        //통계id별로 복습량 갯수의 rank()를 구한다

        LocalDate mon = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate today= LocalDate.now();

        Integer total= jpaQueryFactory
                .select(weekReview.count.sum())
                .where(weekReview.date.between(mon, today))
                .from(weekReview)
                .fetchOne();

        List<Statistics> statisticsList= jpaQueryFactory.selectFrom(statistics)
                                        .fetch();


        for(Statistics st: statisticsList){

            //랭킹을 구한다
            Integer st_sum= jpaQueryFactory
                    .select(weekReview.count.sum())
                    .from(weekReview)
                    .where(weekReview.statistics.id.eq(st.getId())
                            .and(weekReview.date.between(mon, today)))
                    .fetchOne();

            if(st_sum == null) st_sum= 0;
            if(total == null) total= 1;
            Integer ranking= (int) (100- (double) st_sum/total * 100);

            st.setRanking(ranking);


            //연속복습일
            //어제 푼 문제가 없으면 0으로 초기화
            //사용자가 문제를 풀면 +1해주기
            LocalDate yesterday= LocalDate.now().minusDays(1);

                Integer isSolvedYesterday= jpaQueryFactory
                                .selectOne()
                                .from(weekReview)
                                .where(weekReview.date.eq(yesterday)
                                        .and(weekReview.statistics.id.eq(st.getId())))
                                .fetchFirst();


            if(isSolvedYesterday == null)
                st.setSequence(0);

            statisticsRepository.save(st);


        }
    }

}
