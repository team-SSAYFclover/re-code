import React, { useEffect, useState } from 'react';
import { BsTrash } from 'react-icons/bs';
import { HiPencil } from 'react-icons/hi';
import { FaChevronDown, FaChevronUp } from 'react-icons/fa';

export interface ICode {
  id: number;
  name: string;
  content: string;
  reviewStatus: boolean;
  date: string;
}

interface IProblemDetailCodeCompProps extends ICode {
  toggleReviewStatus: () => void;
  deleteCode: () => void;
  onModifyName: (id: number, newName: string) => void;
}

const ProblemDetailCodeComp: React.FC<IProblemDetailCodeCompProps> = ({
  id,
  name,
  content,
  reviewStatus,
  date,
  toggleReviewStatus,
  deleteCode,
  onModifyName,
}) => {
  const [isClicked, setIsClicked] = useState(false);
  const [isChecked, setIsChecked] = useState(reviewStatus);
  const [isModifying, setIsModifying] = useState(false);
  const [modifiedName, setModifiedName] = useState(name);

  useEffect(() => {
    setIsChecked(reviewStatus);
  }, [reviewStatus]);

  const clickShowHandler = () => {
    setIsClicked(!isClicked);
  };
  const toggleReviewHandler = () => {
    toggleReviewStatus();
    setIsChecked(!isChecked);
  };
  const modifyHandler = () => {
    if (isModifying) {
      onModifyName(id, modifiedName);
    }
    setIsModifying(!isModifying);
  };
  const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setModifiedName(e.target.value);
  };

  const lineLength: number = content.split('\n').length;
  const lineArray: number[] = Array.from({ length: lineLength }, (_, i) => i + 1);

  return (
    <div className="w-full h-fit flex flex-col">
      <div className="ms-10 text-MAIN1">{date}일 제출</div>
      <div className="m-3 mt-2 mb-5 p-5 ps-10 pe-10 border border-gray-300 rounded-lg">
        <div className="w-full flex flex-row justify-between">
          {isModifying ? (
            <div>
              <input
                type="text"
                value={modifiedName}
                onChange={handleNameChange}
                className="border border-gray-300 p-1 rounded inline-block"
              />
              <HiPencil
                className="inline ms-2 text-gray-400 cursor-pointer"
                onClick={modifyHandler}
              />
            </div>
          ) : (
            <div>
              {name}
              <HiPencil
                className="inline ms-2 text-gray-400 cursor-pointer"
                onClick={modifyHandler}
              />
            </div>
          )}
          <div className="pt-1 text-MAIN1 cursor-pointer" onClick={clickShowHandler}>
            {isClicked ? <FaChevronUp /> : <FaChevronDown />}
          </div>
        </div>
        {isClicked && (
          <div
            className={`${isClicked ? 'animate-toggleDown m-3 flex flex-col' : 'animate-toggleUp m-3 flex flex-col'}`}
          >
            <div className="flex flex-row justify-between">
              <div className="text-lg">제출 정답 기록</div>
              <label className="inline-flex items-center cursor-pointer">
                <input
                  type="checkbox"
                  checked={isChecked}
                  className="sr-only peer"
                  onChange={toggleReviewHandler}
                />
                <span className="me-3 text-xs text-MAIN1">
                  {isChecked ? '리마인드 ON' : '리마인드 OFF'}
                </span>
                <div className="relative w-11 h-6 bg-MAIN2 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-MAIN1 rounded-full peer peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-MAIN1 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-MAIN1"></div>
              </label>
            </div>
            <div className="h-full flex flex-row mt-3">
              <div className="w-fit p-1 me-2 flex flex-col items-center whitespace-pre-wrap bg-teal-50 text-teal-400">
                {lineArray.map((number) => (
                  <div className="w-fit" key={number}>
                    {number}
                  </div>
                ))}
              </div>
              <div className="whitespace-pre-wrap">{content}</div>
            </div>
            <div className="w-full flex flex-row justify-end">
              <button
                onClick={deleteCode}
                className="p-1 ps-5 pe-5 rounded-2xl border border-red-400 text-sm text-red-400"
              >
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
