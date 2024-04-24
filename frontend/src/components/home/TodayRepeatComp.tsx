import React from 'react';
import TodayRepeatList from './TodayRepeatList';

const TodayRepeatComp: React.FC = () => {
  return (
    <div className="shadow-lg w-full h-full p-5 flex-row">
      <div className="text-sm font-semibold">오늘의 복습 리스트</div>
      <div className="text-xs text-gray-500">지금까지 쌓인 복습 리스트입니다.</div>
      <div>
        <TodayRepeatList />
        <TodayRepeatList />
        <TodayRepeatList />
        <TodayRepeatList />
      </div>
    </div>
  );
};

export default TodayRepeatComp;
