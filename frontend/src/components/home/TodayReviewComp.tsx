import React from 'react';
import { useNavigate } from 'react-router-dom';
import TodayReviewList from './TodayReviewList';

interface TodayReviewCompProps {
  TodayReviewData: {
    codeId: number;
    problemNo: number;
    problemTitle: string;
    count: number;
    solvedYn: boolean;
  }[];
}

const TodayReviewComp: React.FC<TodayReviewCompProps> = ({ TodayReviewData }) => {
  const navigate = useNavigate();
  return (
    <div className="shadow-lg w-full h-[60%] p-5 pb-0 flex flex-col bg-white rounded-sm">
      <div className="w-full flex flex-row justify-between">
        <div className="font-semibold">오늘의 복습 리스트</div>
        <button
          onClick={() => navigate(`/recode`)}
          className="w-fit p-1 px-2 me-1 bg-MAIN2 text-MAIN1 rounded-md text-xs text-center"
        >
          복습하러 가기
        </button>
      </div>
      <div className="text-sm text-gray-500">지금까지 쌓인 복습 리스트입니다.</div>
      <div className="overflow-scroll overflow-x-hidden h-4/5 pe-2 mt-2">
        {TodayReviewData.length > 0 ? (
          TodayReviewData.map((item) => (
            <TodayReviewList
              key={item.codeId}
              codeId={item.codeId}
              problemNo={item.problemNo}
              problemTitle={item.problemTitle}
              count={item.count}
              solvedYn={item.solvedYn}
            />
          ))
        ) : (
          <div className="w-full h-full flex justify-center items-center text-sm">
            오늘의 복습 리스트가 없어요
          </div>
        )}
      </div>
    </div>
  );
};

export default TodayReviewComp;
