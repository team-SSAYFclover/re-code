import React from 'react';

interface RecommendCompProps {
  suppleProb: { problemNo: number; problemTitle: string };
  randProb: { problemNo: number; problemTitle: string };
}

const RecommendComp: React.FC<RecommendCompProps> = ({ suppleProb, randProb }) => {
  return (
    <div className="shadow-lg w-[calc(40vh)] h-full p-10 flex flex-col justify-evenly rounded-sm">
      <div className="text-sm font-normal">오늘의 보충 문제</div>
      <div className="w-full h-fit m-1 p-2 rounded-lg flex-row bg-MAIN1 shadow-inner">
        <div className="flex justify-between">
          <div className="text-sm font-semibold text-MAIN2">BOJ {suppleProb.problemNo}</div>
        </div>
        <div className="font-semibold text-white text-end">{suppleProb.problemTitle}</div>
      </div>
      <div className="text-sm font-normal">오늘의 랜덤 문제</div>
      <div className="w-full h-fit m-1 p-2 rounded-lg flex-row bg-MAIN1 shadow-inner">
        <div className="flex justify-between">
          <div className="text-sm font-semibold text-MAIN2">BOJ {randProb.problemNo}</div>
        </div>
        <div className="font-semibold text-white text-end">{randProb.problemTitle}</div>
      </div>
    </div>
  );
};

export default RecommendComp;
