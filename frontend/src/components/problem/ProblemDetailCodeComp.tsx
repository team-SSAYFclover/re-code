import React, { useState } from 'react';
import { BsTrash } from 'react-icons/bs';
import { HiPencil } from 'react-icons/hi';

export interface ICode {
  id: number;
  name: string;
  content: string;
  reviewStatus: boolean;
  date: string;
}

const ProblemDetailCodeComp: React.FC<ICode> = ({ id, name, content, reviewStatus, date }) => {
  const [isClicked, setIsClicked] = useState(false);
  const clickShowHandler = () => {
    setIsClicked(!isClicked);
  };
  const clickReviewHandler = () => {
    reviewStatus = !reviewStatus;
  };
  return (
    <div className="w-full h-fit flex flex-col">
      <div className="ms-10 text-MAIN1">{date}일 제출</div>
      <div
        className="m-3 mt-2 mb-5 p-5 ps-10 pe-10 border border-gray-300 rounded-lg"
        onClick={clickShowHandler}
      >
        {id}. {name}
        <HiPencil className="inline ms-2 text-gray-400 cursor-pointer" />
        {isClicked && (
          <div className="m-3 flex flex-col">
            <div className="flex flex-row justify-between">
              <div className="text-lg">제출 정답 기록</div>
              <label
                className="inline-flex items-center cursor-pointer"
                onClick={clickReviewHandler}
              >
                <input type="checkbox" value="reviewStatus" className="sr-only peer" />
                {reviewStatus ? (
                  <span className="me-3 text-xs text-MAIN1">리마인드 ON</span>
                ) : (
                  <span className="me-3 text-xs text-MAIN2">리마인드 OFF</span>
                )}
                <div className="relative w-11 h-6 bg-MAIN2 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-MAIN1 rounded-full peer peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-MAIN1 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-MAIN1"></div>
              </label>
            </div>
            <div className="h-full flex flex-row mt-3">
              <div className="w-[3vh] p-1 me-2 flex flex-col text-center bg-teal-50 text-teal-400">
                1 2 3 4 5 6 7 8 9 10
              </div>
              <div>{content}</div>
            </div>
            <div className="w-full flex flex-row justify-end">
              <button className="p-1 ps-5 pe-5 rounded-2xl border border-red-400 text-sm text-red-400">
                <BsTrash className="inline" /> 삭제
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProblemDetailCodeComp;
