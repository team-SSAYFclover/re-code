import defaultProfile from '@/assets/default_profile.png';
import userStore from '@/stores/userStore';
import React from 'react';
import {
  PolarAngleAxis,
  PolarGrid,
  Radar,
  RadarChart,
  ResponsiveContainer,
  Tooltip,
} from 'recharts';

interface IOctagonGraphProps {
  OctagonData: { name: string; num: number }[];
}

const OctagonGraphComp: React.FC<IOctagonGraphProps> = ({ OctagonData }) => {
  const { avatarUrl } = userStore();
  return (
    <div className="shadow-lg p-2 w-1/2 h-full flex-row bg-white rounded-lg mr-4 relative">
      <div className="absolute top-0 bottom-0 left-0 right-0 flex justify-center items-center">
        <img
          src={avatarUrl || defaultProfile}
          alt="profile"
          className="w-8 z-10 rounded-full border border-gray-200"
        />
      </div>
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
