import React from 'react';

const TodayRepeatList: React.FC = () => {
  return (
    <div className="w-full h-fit m-1 p-2 flex-row bg-gray-50">
      <div className="flex justify-between">
        <div className="text-xs font-semibold text-gray-300">BOJ 3302</div>
        <div className="w-fit p-1 bg-MAIN1 text-MAIN2 rounded-md text-xs text-center">
          3번째 복습
        </div>
      </div>
      <div className="text-sm font-semibold">Job Scheduling</div>
    </div>
  );
};

export default TodayRepeatList;
