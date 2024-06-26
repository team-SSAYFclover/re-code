import React from 'react';
import {
  Bar,
  BarChart,
  CartesianGrid,
  Line,
  LineChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from 'recharts';

interface WeekRepeatCompProps {
  weekRepeatData: { date: string; 복습량: number }[];
  percentage: number;
}

const WeekRepeatComp: React.FC<WeekRepeatCompProps> = ({ weekRepeatData, percentage }) => {
  const percentData = [{ name: 'Percent', value: 100 - percentage }];

  return (
    <div className="shadow-xl w-full h-[40%] my-[10px] p-5 flex-row bg-white rounded-lg">
      <div className="w-full flex justify-between h-[80px]">
        <div>
          <div className="font-semibold">주간 복습량</div>
          <div className="text-sm text-gray-500">지난 일주일 간의 복습 횟수입니다</div>
        </div>
        <div className="w-full flex flex-1 flex-col content-end text-end ms-5">
          <div className="text-xs text-MAIN1 font-semibold me-2">현재 상위 {percentage}%</div>
          <div className="min-w-xs ms-5">
            <ResponsiveContainer width="100%" height={15}>
              <BarChart data={percentData} layout="vertical">
                <CartesianGrid fill="#ECFFFA" stroke="none" />
                <YAxis type="category" dataKey="name" hide={true} />
                <XAxis type="number" domain={[0, 100]} hide={true} />
                <Bar dataKey="value" fill="#53EDC7" />
              </BarChart>
            </ResponsiveContainer>
          </div>
        </div>
      </div>
      <div className="w-full h-[calc(100%-80px)]">
        <ResponsiveContainer width="100%" height="100%">
          <LineChart data={weekRepeatData}>
            <CartesianGrid strokeDasharray="0 0" stroke="#ECFFFA" />
            <XAxis
              dataKey="date"
              interval={0}
              stroke="#53EDC7"
              strokeWidth={2}
              tick={{ fill: 'gray', fontSize: 12 }}
            />
            <YAxis hide={true} />
            <Tooltip />
            <Line
              type="monotone"
              dataKey="복습량"
              stroke="#53EDC7"
              strokeWidth={2}
              activeDot={{ r: 8 }}
            />
          </LineChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
};

export default WeekRepeatComp;
