import React from 'react';
import {
  LineChart,
  BarChart,
  Line,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from 'recharts';

interface WeekRepeatCompProps {
  weekRepeatData: { date: string; num: number }[];
  percentage: number;
}

const WeekRepeatComp: React.FC<WeekRepeatCompProps> = ({ weekRepeatData, percentage }) => {
  const percentData = [{ name: 'Percent', value: percentage }];

  return (
    <div className="shadow-xl w-full h-fit p-5 flex-row bg-white rounded-lg">
      <div className="w-full flex justify-between">
        <div>
          <div className="text-sm font-semibold">주간 복습량</div>
          <div className="text-xs text-gray-500">지난 일주일 간의 복습 횟수입니다</div>
        </div>
        <div className="w-full flex flex-1 flex-col justify-end content-end text-end ms-5">
          <div className="text-xs text-MAIN1 font-semibold me-2">현재 상위 {percentage}%</div>
          <div className="max-w-xs ms-5">
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
      <div className="w-full h-fit">
        <ResponsiveContainer width="100%" height={150}>
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
              dataKey="num"
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
