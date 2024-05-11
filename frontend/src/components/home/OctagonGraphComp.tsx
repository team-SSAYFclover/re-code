import React from 'react';
import {
  Radar,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  Tooltip,
  ResponsiveContainer,
} from 'recharts';

interface IOctagonGraphProps {
  OctagonData: { name: string; num: number }[];
}

const OctagonGraphComp: React.FC<IOctagonGraphProps> = ({ OctagonData }) => {
  return (
    <div className="shadow-lg p-1 w-[calc(40vh)] h-full flex-row bg-white rounded-lg">
      <ResponsiveContainer width="100%" height="100%">
        <RadarChart data={OctagonData}>
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
