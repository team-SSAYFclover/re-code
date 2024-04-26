import React from 'react';

interface TodayReviewListProps {
  problemNo: number;
  problemTitle: string;
  count: number;
  solvedYn: boolean;
}

const TodayReviewList: React.FC<TodayReviewListProps> = ({
  problemNo,
  problemTitle,
  count,
  solvedYn,
}) => {
  return solvedYn ? (
    <div className="w-full h-fit m-1 p-2 rounded-lg flex-row bg-gray-200 shadow-inner">
      <div className="flex justify-between">
        <div className="text-xs font-semibold text-gray-500">BOJ {problemNo}</div>
        <div className="w-fit p-1 bg-MAIN2 text-MAIN1 rounded-md text-xs text-center">
          {count}번째 복습
        </div>
      </div>
      <div className="text-sm font-semibold">{problemTitle}</div>
    </div>
  ) : (
    <div className="w-full h-fit m-1 p-2 rounded-lg flex-row bg-gray-50">
      <div className="flex justify-between">
        <div className="text-xs font-semibold text-gray-300">BOJ {problemNo}</div>
        <div className="w-fit p-1 bg-MAIN1 text-MAIN2 rounded-md text-xs text-center">
          {count}번째 복습
        </div>
      </div>
      <div className="text-sm font-semibold">{problemTitle}</div>
    </div>
  );
};

export default TodayReviewList;
