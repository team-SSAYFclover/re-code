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
  { name: 'greedy', num: 16 },
  { name: 'string', num: 20 },
  { name: '자료구조', num: 15 },
  { name: 'graph', num: 31 },
  { name: 'dp', num: 26 },
  { name: 'geometry', num: 6 },
  { name: 'math', num: 5 },
  { name: '구현', num: 27 },
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
