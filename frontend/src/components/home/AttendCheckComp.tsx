import React from 'react';
import AttendCheckCircle from './AttendCheckCircle';

interface IWeekRepeatData {
  weekRepeatData: {
    date: string;
    복습량: number;
  }[];
}

const AttendCheckComp: React.FC<IWeekRepeatData> = ({ weekRepeatData }) => {
  return (
    <div className="shadow-lg w-full h-[25%] p-5 bg-white rounded-lg flex flex-col">
      <div className="font-semibold">출석 체크</div>
      <div className="text-sm text-gray-500">지난 일주일 간의 학습 여부입니다</div>
      <div className="flex flex-1 justify-evenly items-center pt-3 pb-1 overflow-scroll scrollbar-hide">
        {weekRepeatData.map((data, index) => (
          <AttendCheckCircle key={index} weekday={index} onoff={data.복습량 > 0} />
        ))}
      </div>
    </div>
  );
};

export default AttendCheckComp;
