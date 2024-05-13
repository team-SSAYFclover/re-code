import React from 'react';
import AttendCheckCircle from './AttendCheckCircle';

interface IWeekRepeatData {
  weekRepeatData: {
    date: string;
    num: number;
  }[];
}

const AttendCheckComp: React.FC<IWeekRepeatData> = ({ weekRepeatData }) => {
  return (
    <div className="shadow-lg w-full h-fit p-5 bg-white rounded-lg">
      <div className="text-sm font-semibold">출석 체크</div>
      <div className="text-xs text-gray-500">지난 일주일 간의 학습 여부입니다</div>
      <div className="flex justify-evenly pt-3 pb-1 overflow-scroll scrollbar-hide">
        {weekRepeatData.map((data, index) => (
          <AttendCheckCircle key={index} weekday={index} onoff={data.num > 0} />
        ))}
      </div>
    </div>
  );
};

export default AttendCheckComp;
