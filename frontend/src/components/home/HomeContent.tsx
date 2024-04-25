import React from 'react';
import AttendCheckComp from './AttendCheckComp';
import WeekReviewComp from './WeekReviewComp';
import TodayReviewComp from './TodayReviewComp';
import OctagonGraphComp from './OctagonGraphComp';
import CloverComp from './CloverComp';
import RecommendComp from './RecommendComp';

const data1 = [
  { date: '월', num: 4 },
  { date: '화', num: 3 },
  { date: '수', num: 0 },
  { date: '목', num: 0 },
  { date: '금', num: 1 },
  { date: '토', num: 6 },
  { date: '일', num: 2 },
];
const data2 = 60;
const TodayReviewData = [
  { problemNo: 3302, problemTitle: 'Job Scheduling', count: 2, solvedYn: true },
  { problemNo: 11779, problemTitle: '최소비용 구하기 2', count: 1, solvedYn: false },
  { problemNo: 1486, problemTitle: '등산', count: 1, solvedYn: true },
  { problemNo: 1261, problemTitle: '알고스팟', count: 2, solvedYn: false },
  { problemNo: 11559, problemTitle: 'Puyo Puyo', count: 2, solvedYn: false },
  { problemNo: 1197, problemTitle: '최소 스패닝 트리', count: 3, solvedYn: false },
  { problemNo: 17471, problemTitle: '게리맨더링', count: 1, solvedYn: false },
];
const suppleProb = {
  problemNo: 3302,
  problemTitle: 'Job Scheduling',
};
const randProb = {
  problemNo: 11779,
  problemTitle: '최소비용 구하기 2',
};

const HomeContent: React.FC = () => {
  return (
    <div className="w-full h-full">
      {/* 상단 인사부분 */}
      <div>
        <div className="text-lg">
          <span className="font-semibold">김싸피</span>님 복습{' '}
          <span className="font-semibold">
            {' '}
            연속 <span className="text-MAIN1">1</span>일째
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
          <AttendCheckComp />
          <WeekReviewComp weekRepeatData={data1} percentage={data2} />
          <div className="flex justify-between h-1/3">
            <CloverComp />
            <RecommendComp suppleProb={suppleProb} randProb={randProb} />
          </div>
        </div>
        {/* 우측 */}
        <div className="w-1/3 h-full flex flex-col justify-between">
          <TodayReviewComp TodayReviewData={TodayReviewData} />
          <OctagonGraphComp />
        </div>
      </div>
    </div>
  );
};

export default HomeContent;
