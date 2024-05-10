import React from 'react';
import TodayReviewList from './TodayReviewList';
import { useNavigate } from 'react-router-dom';

interface TodayReviewCompProps {
  TodayReviewData: { problemNo: number; problemTitle: string; count: number; solvedYn: boolean }[];
}

const TodayReviewComp: React.FC<TodayReviewCompProps> = ({ TodayReviewData }) => {
  const navigate = useNavigate();
  return (
    <div className="shadow-lg w-full h-3/5 p-5 pb-0 flex flex-col bg-white rounded-sm">
      <div className="w-full flex flex-row justify-between">
        <div className="text-sm font-semibold">오늘의 복습 리스트</div>
        <button
          onClick={() => navigate(`/recode`)}
          className="w-fit p-1 me-1 bg-MAIN2 text-MAIN1 rounded-md text-xs text-center"
        >
          복습하러 가기
        </button>
      </div>
      <div className="text-xs text-gray-500">지금까지 쌓인 복습 리스트입니다.</div>
      <div className="overflow-scroll overflow-x-hidden h-4/5 pe-2 mt-2">
        {TodayReviewData.map((item) => (
          <TodayReviewList
            key={item.problemNo}
            problemNo={item.problemNo}
            problemTitle={item.problemTitle}
            count={item.count}
            solvedYn={item.solvedYn}
          />
        ))}
      </div>
    </div>
  );
};

export default TodayReviewComp;
