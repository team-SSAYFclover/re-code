import React from 'react';
import { FaCheck } from 'react-icons/fa6';

const weekdays: string[] = ['월', '화', '수', '목', '금', '토', '일'];
interface AttendCheckCircleProps {
  weekday: number;
  onoff: boolean;
}

const AttendCheckCircle: React.FC<AttendCheckCircleProps> = ({ weekday, onoff }) => {
  function renderCircle() {
    if (onoff) {
      return (
        <div>
          <div className="rounded-full h-12 w-12 bg-MAIN1 content-center text-MAIN2 font-bold">
            <FaCheck className="ms-auto me-auto h-7 w-7" />
          </div>
        </div>
      );
    } else {
      return (
        <div>
          <div className="rounded-full h-12 w-12 bg-MAIN2 text-center content-center text-MAIN1 font-bold">
            {weekdays[weekday]}
          </div>
        </div>
      );
    }
  }

  return <div>{renderCircle()}</div>;
};

export default AttendCheckCircle;
