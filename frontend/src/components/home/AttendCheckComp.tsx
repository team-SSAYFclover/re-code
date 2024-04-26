import React from 'react';
import AttendCheckCircle from './AttendCheckCircle';

const AttendCheckComp: React.FC = () => {
  return (
    <div className="shadow-lg w-full h-fit p-5 bg-white rounded-lg">
      <div className="text-sm font-semibold">출석 체크</div>
      <div className="text-xs text-gray-500">지난 일주일 간의 학습 여부입니다</div>
      <div className="flex justify-evenly pt-3 pb-1 overflow-scroll scrollbar-hide">
        <AttendCheckCircle weekday={0} onoff={true} />
        <AttendCheckCircle weekday={1} onoff={false} />
        <AttendCheckCircle weekday={2} onoff={true} />
        <AttendCheckCircle weekday={3} onoff={false} />
        <AttendCheckCircle weekday={4} onoff={false} />
        <AttendCheckCircle weekday={5} onoff={false} />
        <AttendCheckCircle weekday={6} onoff={false} />
      </div>
    </div>
  );
};

export default AttendCheckComp;
