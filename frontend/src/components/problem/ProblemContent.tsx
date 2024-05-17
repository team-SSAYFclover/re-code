import NoContent from '@/assets/lotties/noContent.json';
import { useIntersectionObserver } from '@/hooks/@common/useIntersectionObserver';
import { useProbList } from '@/hooks/problem/useProblem';
import { IGetProbListParams } from '@/types/problem';
import React, { useEffect, useMemo, useState } from 'react';
import { IoSearchSharp } from 'react-icons/io5';
import { PiFunnelBold } from 'react-icons/pi';
import Lottie from 'react-lottie';
import Nocontent from '../recode/Nocontent';
import ProblemComp from './ProblemComp';
import ProblemOptionComp from './ProblemOptionComp';

export interface IOptionInfo {
  category: { name: string; TF: boolean }[];
  levelStart: number;
  levelEnd: number;
}
const ProblemContent: React.FC = () => {
  const [showOption, setShowOption] = useState<boolean>(false);
  const [searchKeyword, setSearchKeyword] = useState('');
  const [optionInfo, setOptionInfo] = useState<IOptionInfo>({
    category: [
      { name: '수학', TF: false },
      { name: '구현', TF: false },
      { name: 'greedy', TF: false },
      { name: 'string', TF: false },
      { name: '자료 구조', TF: false },
      { name: '그래프', TF: false },
      { name: 'dp', TF: false },
      { name: 'geometry', TF: false },
    ],
    levelStart: 1,
    levelEnd: 30,
    // 브론즈, 실버, 골드, 플래티넘, 다이아몬드, 루비 각 5-4-3-2-1 순
  });
  const handleOptionBtn = () => {
    setShowOption((prev) => !prev);
  };

  const queryParams = useMemo(() => {
    return {
      page: 0,
      size: 6,
      start: optionInfo.levelStart,
      end: optionInfo.levelEnd,
      tag: optionInfo.category.filter((c) => c.TF).map((c) => c.name),
      keyword: searchKeyword,
    };
  }, [optionInfo, searchKeyword]);

  const [queryParamsToSend, setQueryParamsToSend] = useState<IGetProbListParams>({
    page: 0,
    size: 6,
    start: 1,
    end: 30,
    tag: [],
    keyword: '',
  });

  const { useGetProbList } = useProbList();
  const { data, fetchNextPage, hasNextPage } = useGetProbList(queryParamsToSend, true);
  const problemData = data?.pages.flatMap((page) => page.data.content) || [];

  const handleSearchClick = () => {
    setQueryParamsToSend(queryParams);
  };

  const { setTarget } = useIntersectionObserver({
    hasNextPage,
    fetchNextPage,
  });

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      handleSearchClick();
    }
  };

  useEffect(() => {
    handleSearchClick();
  }, []);

  const defaltOptions = {
    loop: true,
    autoPlay: true,
    animationData: NoContent,
  };

  return (
    <div className="w-full h-full pt-10 px-20">
      {/* 상단 옵션버튼, 검색창 */}
      <div className="w-full flex justify-between">
        <button
          className={`rounded-md font-bold px-3 tracking-wider ${showOption ? 'bg-MAIN1 text-MAIN2' : 'bg-MAIN2 text-MAIN1'} flex justify-center`}
          onClick={() => handleOptionBtn()}
        >
          <PiFunnelBold className="me-1 mt-auto mb-auto" size={20} />
          <div className="inline-block mt-auto mb-auto">Filter</div>
        </button>
        <form className="w-full ms-5">
          <div className="relative">
            <IoSearchSharp
              className="absolute inset-y-0 start-0 mt-auto mb-auto ms-3"
              size={20}
              color="gray"
            />
            <input
              type="text"
              id="probSearch"
              className="block w-full p-4 ps-10 bg-gray-50 rounded-lg text-gray-700 focus:outline-none focus:ring-2 focus:ring-MAIN1"
              placeholder="원하는 검색어를 입력하세요"
              value={searchKeyword}
              onChange={(e) => setSearchKeyword(e.target.value)}
              onKeyDown={handleKeyDown}
              required
            />
            <button
              type="button"
              className="absolute inset-y-0 end-0 mt-auto mb-auto me-3 ps-3 pe-3 text-gray-400"
              onClick={handleSearchClick}
            >
              search
            </button>
          </div>
        </form>
      </div>
      {/* 옵션 컴포넌트 */}
      {showOption ? (
        <ProblemOptionComp
          optionInfo={optionInfo}
          setOptionInfo={setOptionInfo}
          handleSearchClick={handleSearchClick}
        />
      ) : null}
      {/* 문제 컴포넌트 리스트 */}
      <div className="w-full pt-10 pb-4 flex flex-row flex-wrap overflow-x-auto">
        {problemData.length > 0 ? (
          problemData.map((item) => (
            <ProblemComp
              key={item.problemNo}
              problemNo={item.problemNo}
              title={item.title}
              level={item.level}
              tags={item.tags}
              repeatCount={item.reviewCount}
            />
          ))
        ) : (
          <div className="w-full min-h-[500px]">
            <Nocontent
              text={
                <>
                  <div className="text-lg">
                    오늘의 복습문제가 없어요 <br />
                    문제를 풀어 복습 문제를 추가해볼까요?
                  </div>
                  <button
                    className="border text-sm border-MAIN1 text-MAIN1 px-4 py-2 mt-4 rounded-md hover:text-white hover:bg-MAIN1"
                    onClick={() => window.open('https://www.acmicpc.net', '_blank')}
                  >
                    문제 풀러 가기
                  </button>
                </>
              }
              icon={<Lottie options={defaltOptions} width={160} height={160} />}
            />
          </div>
        )}
      </div>

      <div ref={setTarget} className="h-[1rem]" key={data?.pageParams.length}></div>
    </div>
  );
};

export default ProblemContent;
