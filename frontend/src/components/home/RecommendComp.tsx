import { useHome } from '@/hooks/home/useHome';
import React from 'react';
import { IoMdRefresh } from 'react-icons/io';
import { useAnimate } from 'framer-motion';

interface RecommendCompProps {
  suppleProb: { problemNo: number; problemTitle: string };
  randProb: { problemNo: number; problemTitle: string };
}

let randTimeout = false;
let suppledTimeout = false;

const RecommendComp: React.FC<RecommendCompProps> = ({ suppleProb, randProb }) => {
  const { usePatchRandomProblem, usePatchSuppleProblem } = useHome();
  const { mutate: randomProblemMutate } = usePatchRandomProblem();
  const { mutate: suppleProblemMutate } = usePatchSuppleProblem();
  const [scope, animate] = useAnimate();

  const handleModifyRandomProblem = () => {
    if (randTimeout) return;
    randTimeout = true;
    randomProblemMutate(undefined, {
      onSuccess: () => {
        animate('#randProb', { opacity: [0, 1] });
      },
    });
    setTimeout(() => {
      randTimeout = false;
    }, 500);
  };

  const handleModifySuppleProblem = () => {
    if (suppledTimeout) return;
    suppledTimeout = true;
    suppleProblemMutate(undefined, {
      onSuccess: () => {
        animate('#suppleProb', { opacity: [0, 1] });
      },
    });
    setTimeout(() => {
      suppledTimeout = false;
    }, 500);
  };

  return (
    <div
      className="shadow-lg w-1/2 h-full p-4 flex flex-col justify-evenly bg-white rounded-lg"
      ref={scope}
    >
      <div className="flex justify-between items-center">
        <div className="text-sm font-semibold">오늘의 보충 문제</div>
        <IoMdRefresh
          color="#53EDC7"
          className="hover:cursor-pointer"
          onClick={handleModifySuppleProblem}
        />
      </div>
      <div
        id="suppleProb"
        onClick={() => window.open(`https://www.acmicpc.net/problem/${suppleProb.problemNo}`)}
        className="w-full h-fit m-1 p-2 rounded-lg flex-row bg-[#9BFFC3] shadow-inner cursor-pointer"
      >
        <div className="flex justify-between">
          <div className="text-xs font-semibold text-MAIN2">BOJ {suppleProb.problemNo}</div>
        </div>
        <div className="text-sm font-semibold text-white text-end">{suppleProb.problemTitle}</div>
      </div>
      <div className="flex justify-between items-center">
        <div className="text-sm font-semibold">오늘의 랜덤 문제</div>
        <IoMdRefresh
          color="#53EDC7"
          className="hover:cursor-pointer"
          onClick={handleModifyRandomProblem}
        />
      </div>
      <div
        id="randProb"
        onClick={() => window.open(`https://www.acmicpc.net/problem/${randProb.problemNo}`)}
        className="w-full h-fit m-1 p-2 rounded-lg flex-row bg-[#5BF298] shadow-inner cursor-pointer"
      >
        <div className="flex justify-between">
          <div className="text-xs font-semibold text-MAIN2">BOJ {randProb.problemNo}</div>
        </div>
        <div className="text-sm font-semibold text-white text-end">{randProb.problemTitle}</div>
      </div>
    </div>
  );
};

export default RecommendComp;
