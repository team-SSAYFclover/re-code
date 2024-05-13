import React from 'react';
import AttendCheckComp from './AttendCheckComp';
import WeekReviewComp from './WeekReviewComp';
import TodayReviewComp from './TodayReviewComp';
import OctagonGraphComp from './OctagonGraphComp';
import CloverComp from './CloverComp';
import RecommendComp from './RecommendComp';
import { useHome } from '@/hooks/home/useHome';

const suppleProb = {
  problemNo: 3302,
  problemTitle: 'Job Scheduling',
};
const randProb = {
  problemNo: 11779,
  problemTitle: '최소비용 구하기 2',
};

const HomeContent: React.FC = () => {
  const { useGetMainInfo } = useHome();
  const { data, isLoading } = useGetMainInfo();
  console.log('가져온 데이터 : ', data);
  console.log('isLoading : ', isLoading);
  // API에서 받은 weekReviews 객체를 배열로 변환
  const weekRepeatData = data
    ? [
        { date: '월', num: data.weekReviews.mon },
        { date: '화', num: data.weekReviews.tue },
        { date: '수', num: data.weekReviews.wed },
        { date: '목', num: data.weekReviews.thu },
        { date: '금', num: data.weekReviews.fri },
        { date: '토', num: data.weekReviews.sat },
        { date: '일', num: data.weekReviews.sun },
      ]
    : [];

  const OctagonData = data
    ? Object.keys(data.algoReview).map((key) => ({
        name: key,
        num: data.algoReview[key],
      }))
    : [];

  const TodayReviewData = data
    ? data.todayProblems.map((problem) => ({
        codeId: problem.codeId,
        problemNo: problem.problemNo,
        problemTitle: problem.title,
        count: problem.reviewCnt,
        solvedYn: problem.completed,
      }))
    : [];

  const solvedCount = TodayReviewData.filter((problem) => problem.solvedYn).length;
  const totalProblems = TodayReviewData.length;
  const leaf = totalProblems > 0 ? Math.floor((solvedCount / totalProblems) * 4) : 0;

  if (isLoading) return <div>Loading...</div>;
  if (!data) return <div>No data available</div>;

  return (
    <div className="w-full h-full">
      {/* 상단 인사부분 */}
      <div>
        <div className="text-lg">
          <span className="font-semibold">김싸피</span>님 복습{' '}
          <span className="font-semibold">
            {' '}
            연속 <span className="text-MAIN1">{data.sequence}</span>일째
          </span>
          예요!
        </div>
        <div className="text-gray-400 text-sm">
          <span className="text-MAIN1 font-semibold">Re:code</span>에서 함께 알고리즘 복습 해볼까요?
        </div>
      </div>
      <div className="w-full h-full flex flex-row justify-between">
        {/* 좌측 */}
        <div className="w-3/5 h-full flex flex-col justify-between">
          <AttendCheckComp weekRepeatData={weekRepeatData} />
          <WeekReviewComp weekRepeatData={weekRepeatData} percentage={data.ranking} />
          {/* 좌측 하단 */}
          <div className="flex justify-between h-1/3">
            <OctagonGraphComp OctagonData={OctagonData} />
            <RecommendComp suppleProb={suppleProb} randProb={randProb} />
          </div>
        </div>
        {/* 우측 */}
        <div className="w-1/3 h-full flex flex-col justify-between">
          <CloverComp leafNum={leaf} />
          <TodayReviewComp TodayReviewData={TodayReviewData} />
        </div>
      </div>
    </div>
  );
};

export default HomeContent;
