import React from 'react';
import { Range, getTrackBackground } from 'react-range';

interface ProblemOptionCompProps {
  optionInfo: IOptionInfo;
  setOptionInfo: React.Dispatch<React.SetStateAction<IOptionInfo>>;
}
interface LevelNames {
  [key: number]: string;
}

const levelNames: LevelNames = {
  1: '브론즈 5',
  2: '브론즈 4',
  3: '브론즈 3',
  4: '브론즈 2',
  5: '브론즈 1',
  6: '실버 5',
  7: '실버 4',
  8: '실버 3',
  9: '실버 2',
  10: '실버 1',
  11: '골드 5',
  12: '골드 4',
  13: '골드 3',
  14: '골드 2',
  15: '골드 1',
  16: '플래티넘 5',
  17: '플래티넘 4',
  18: '플래티넘 3',
  19: '플래티넘 2',
  20: '플래티넘 1',
  21: '다이아몬드 5',
  22: '다이아몬드 4',
  23: '다이아몬드 3',
  24: '다이아몬드 2',
  25: '다이아몬드 1',
  26: '루비 5',
  27: '루비 4',
  28: '루비 3',
  29: '루비 2',
  30: '루비 1',
};
const barColorForValue = (value: number): string => {
  if (value <= 5) return '#ad5600';
  if (value <= 10) return '#435f7a';
  if (value <= 15) return '#ec9a00';
  if (value <= 20) return '#75e4ae';
  if (value <= 25) return '#00b4fc';
  return '#ff0062'; // 26 이상
};

const ProblemOptionComp: React.FC<ProblemOptionCompProps> = ({ optionInfo, setOptionInfo }) => {
  const toggleCategory = (index: number) => {
    const newCategories = [...optionInfo.category];
    newCategories[index].TF = !newCategories[index].TF;
    setOptionInfo({ ...optionInfo, category: newCategories });
  };

  return (
    <div className="h-fit w-full z-10 mt-1 p-10 shadow-md flex flex-col bg-white rounded-lg overflow-scroll scrollbar-hide">
      <div className="flex flex-col">
        <div>알고리즘 분류</div>
        <div className="flex flex-row overflow-x-auto">
          {optionInfo.category.map((item, index) => (
            <button
              key={index}
              onClick={() => toggleCategory(index)}
              className={`min-w-24 rounded-xl w-fit p-1 ps-2 pe-2 ${item.TF ? 'bg-MAIN1 text-MAIN2' : 'bg-gray-100 text-gray-400'} m-1`}
            >
              {item.name}
            </button>
          ))}
        </div>
      </div>
      <div className="flex flex-col mt-5">
        <div>문제 난이도</div>
        <label htmlFor="levelRange" className="mt-2">
          <div className="inline min-w-28 rounded-xl w-fit font-bold p-1 ps-4 pe-4 me-3 bg-gray-100 text-gray-400">
            {levelNames[optionInfo.levelStart]}
          </div>
          -
          <div className="inline min-w-28 rounded-xl w-fit font-bold p-1 ps-4 pe-4 ms-3 bg-gray-100 text-gray-400">
            {levelNames[optionInfo.levelEnd]}
          </div>
        </label>
        <Range
          values={[optionInfo.levelStart, optionInfo.levelEnd]}
          step={1}
          min={1}
          max={30}
          onChange={(values) =>
            setOptionInfo((prev: IOptionInfo) => ({
              ...prev,
              levelStart: values[0],
              levelEnd: values[1],
            }))
          }
          renderTrack={({ props, children }) => (
            <div
              onMouseDown={props.onMouseDown}
              onTouchStart={props.onTouchStart}
              style={{
                ...props.style,
                height: '36px',
                display: 'flex',
                width: '100%',
              }}
            >
              <div
                ref={props.ref}
                style={{
                  height: '5px',
                  width: '100%',
                  borderRadius: '4px',
                  background: getTrackBackground({
                    values: [optionInfo.levelStart, optionInfo.levelEnd],
                    colors: [
                      barColorForValue(optionInfo.levelStart),
                      '#53EDC7',
                      barColorForValue(optionInfo.levelEnd),
                    ],
                    min: 1,
                    max: 30,
                  }),
                  alignSelf: 'center',
                }}
              >
                {children}
              </div>
            </div>
          )}
          renderThumb={({ props, isDragged }) => (
            <div
              {...props}
              style={{
                ...props.style,
                height: '20px',
                width: '20px',
                borderRadius: '50%',
                backgroundColor: '#FFF',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                boxShadow: '0px 2px 6px #AAA',
              }}
            >
              <div
                style={{
                  height: '16px',
                  width: '5px',
                  backgroundColor: isDragged ? '#548BF4' : '#CCC',
                }}
              />
            </div>
          )}
        ></Range>
      </div>
    </div>
  );
};

export default ProblemOptionComp;
