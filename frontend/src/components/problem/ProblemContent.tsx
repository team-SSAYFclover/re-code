import React, { useState } from 'react';
import ProblemOptionComp from './ProblemOptionComp';
import ProblemComp from './ProblemComp';
import { PiFunnelBold } from 'react-icons/pi';
import { IoSearchSharp } from 'react-icons/io5';

export interface IOptionInfo {
  category: { TF: boolean; tagId: number; name: string }[];
  levelStart: number;
  levelEnd: number;
}
export interface IProblemData {
  problemNo: number;
  title: string;
  level: number;
  tagName: string[];
  repeatNum: number;
}

const problemData: IProblemData[] = [
  {
    problemNo: 1261,
    title: '알고스팟',
    level: 14,
    tagName: ['다익스트라', '최단경로'],
    repeatNum: 2,
  },
  {
    problemNo: 1261,
    title: '알고스팟',
    level: 14,
    tagName: ['다익스트라', '최단경로'],
    repeatNum: 2,
  },
];

const ProblemContent: React.FC = () => {
  const [showOption, setShowOption] = useState<boolean>(false);
  const [optionInfo, setOptionInfo] = useState<IOptionInfo>({
    category: [
      { name: '수학', tagId: 1, TF: false },
      { name: '구현', tagId: 2, TF: false },
      { name: 'greedy', tagId: 3, TF: false },
      { name: 'string', tagId: 4, TF: false },
      { name: '자료 구조', tagId: 5, TF: false },
      { name: '그래프', tagId: 6, TF: false },
      { name: 'dp', tagId: 7, TF: false },
      { name: 'geometry', tagId: 8, TF: false },
    ],
    levelStart: 1,
    levelEnd: 30,
    // 브론즈, 실버, 골드, 플래티넘, 다이아몬드, 루비 각 5-4-3-2-1 순
  });
  const handleOptionBtn = () => {
    if (showOption) {
      setShowOption(false);
    } else {
      setShowOption(true);
    }
  };

  return (
    <div className="w-full h-full pt-10 pe-10">
      {/* 상단 옵션버튼, 검색창 */}
      <div className="w-full flex justify-between">
        <button
          className={`rounded-md font-bold ps-7 pe-7 tracking-wider ${showOption ? 'bg-MAIN1 text-MAIN2' : 'bg-MAIN2 text-MAIN1'} flex justify-center`}
          onClick={() => handleOptionBtn()}
        >
          <PiFunnelBold className="me-1 mt-auto mb-auto" size={20} />
          <div className="inline-block mt-auto mb-auto">Filter</div>
        </button>
        <form className="w-11/12 ms-5">
          <div className="relative">
            <IoSearchSharp
              className="absolute inset-y-0 start-0 mt-auto mb-auto ms-3"
              size={20}
              color="gray"
            />
            <input
              type="search"
              id="probSearch"
              className="block w-full p-4 ps-10 bg-gray-50 rounded-lg text-gray-700 focus:outline-none focus:ring-2 focus:ring-MAIN1"
              placeholder="원하는 검색어를 입력하세요"
              required
            />
          </div>
        </form>
      </div>
      {/* 옵션 컴포넌트 */}
      {showOption ? (
        <ProblemOptionComp optionInfo={optionInfo} setOptionInfo={setOptionInfo} />
      ) : null}
      {/* 문제 컴포넌트 리스트 */}
      {showOption ? (
        <div className="w-full h-1/2 pt-10 pb-4 pe-3 flex flex-row flex-wrap overflow-x-auto">
          {problemData.map((item) => (
            <ProblemComp
              key={item.problemNo}
              problemNo={item.problemNo}
              title={item.title}
              level={item.level}
              tagName={item.tagName}
              repeatNum={item.repeatNum}
            />
          ))}
        </div>
      ) : (
        <div className="w-full h-[calc(74vh)] pt-10 pb-4 pe-3 flex flex-row flex-wrap overflow-x-auto">
          {problemData.map((item) => (
            <ProblemComp
              key={item.problemNo}
              problemNo={item.problemNo}
              title={item.title}
              level={item.level}
              tagName={item.tagName}
              repeatNum={item.repeatNum}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default ProblemContent;
