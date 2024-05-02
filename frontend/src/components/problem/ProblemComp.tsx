import React from 'react';
import { IProblemData } from './ProblemContent';

const ProblemComp: React.FC<IProblemData> = ({ problemNo, title, level, tagName, repeatNum }) => {
  return (
    <div className="shadow-lg w-72 h-40 me-10 mb-5 p-5 flex flex-col justify-between bg-white rounded-md text-sm">
      {/* 상단 */}
      <div className="w-full h-fit flex flex-row justify-between">
        <div>
          <div className="inline rounded-xl w-fit p-1 ps-4 pe-4 m-2 bg-MAIN2 text-MAIN1">BOJ</div>
          <div className="inline text-gray-400">{problemNo}</div>
        </div>
        <div>
          <div className="inline rounded-md text-xs w-fit p-1 ps-3 pe-3 bg-MAIN1 text-MAIN2">
            복습 {repeatNum}회
          </div>
        </div>
      </div>
      {/* 중단 */}
      <div className="w-full h-2/3 p-2 text-lg font-bold">
        {/* level에 맞는 solved ac 매달 그림 삽입 */}
        <div>
          {title}({level})
        </div>
      </div>
      {/* 하단 */}
      <div className="w-full flex flex-row text-xs">
        {tagName.map((item) => (
          <div className="rounded-md me-2 p-1 ps-2 pe-2 bg-gray-100 text-gray-400">{item}</div>
        ))}
      </div>
    </div>
  );
};

export default ProblemComp;
