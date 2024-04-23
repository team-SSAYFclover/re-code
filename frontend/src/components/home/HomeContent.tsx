import React from 'react';
import AttendCheckComp from './AttendCheckComp';
import WeekRepeatComp from './WeekRepeatComp';
import TodayRepeatComp from './TodayRepeatComp';
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

const HomeContent: React.FC = () => {
  return (
    <div className="w-full h-full">
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
      <div className="w-full h-full flex p-3 justify-between">
        <div className="w-2/3 flex-col space-y-3.5 pe-10">
          <AttendCheckComp />
          <WeekRepeatComp weekRepeatData={data1} percentage={data2} />
          <div className="flex">
            <div className="w-1/2 me-5">
              <CloverComp />
            </div>
            <div className="w-1/2 ms-5">
              <RecommendComp />
            </div>
          </div>
        </div>
        <div className="w-1/3 h-full">
          <div className="h-2/3">
            <TodayRepeatComp />
          </div>
          <div className="h-1/3">
            <OctagonGraphComp />
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomeContent;
