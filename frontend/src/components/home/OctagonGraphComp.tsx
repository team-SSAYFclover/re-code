import React from 'react';
import {
  Radar,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  Tooltip,
  ResponsiveContainer,
} from 'recharts';

const data = [
  { name: '수학', tagId: 1, num: 16 },
  { name: '구현', tagId: 2, num: 12 },
  { name: 'greedy', tagId: 3, num: 5 },
  { name: 'string', tagId: 4, num: 7 },
  { name: '자료 구조', tagId: 5, num: 3 },
  { name: '그래프', tagId: 6, num: 9 },
  { name: 'dp', tagId: 7, num: 20 },
  { name: 'geometry', tagId: 8, num: 15 },
];
const OctagonGraphComp: React.FC = () => {
  return (
    <div className="shadow-lg p-1 w-[calc(40vh)] h-full flex-row bg-white rounded-lg">
      <ResponsiveContainer width="100%" height="100%">
        <RadarChart data={data}>
          <PolarGrid />
          <Tooltip />
          <PolarAngleAxis dataKey="name" fontSize={12} />
          <Radar dataKey="num" stroke="#53EDC7" fill="#ECFFFA" fillOpacity={0.5} />
        </RadarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default OctagonGraphComp;
