import React from 'react';
import TodayReviewList from './TodayReviewList';

interface TodayReviewCompProps {
  TodayReviewData: { problemNo: number; problemTitle: string; count: number; solvedYn: boolean }[];
}

const TodayReviewComp: React.FC<TodayReviewCompProps> = ({ TodayReviewData }) => {
  return (
    <div className="shadow-lg w-full h-3/5 p-5 pb-0 flex-row bg-white rounded-sm">
      <div className="text-sm font-semibold">오늘의 복습 리스트</div>
      <div className="text-xs text-gray-500">지금까지 쌓인 복습 리스트입니다.</div>
      <div className="overflow-scroll scrollbar-hide h-4/5 pe-2 mt-2">
        {TodayReviewData.map((item) => (
          <TodayReviewList
            key={item.problemNo}
            problemNo={item.problemNo}
            problemTitle={item.problemTitle}
            count={item.count}
            solvedYn={item.solvedYn}
          />
        ))}
      </div>
    </div>
  );
};

export default TodayReviewComp;
