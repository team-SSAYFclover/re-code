import { ReactComponent as Check } from '@/assets/check.svg';
import { Dispatch, SetStateAction, useState } from 'react';
import { Tooltip } from 'react-tooltip';
import Toast from '../@common/Toast';
import ErrorTooltip from './Tooltip/ErrorTooltip';
import SuccessTooltip from './Tooltip/SuccessTooltip';
const timeout: NodeJS.Timeout[] = [];

const RecodeSolveBox = ({
  codeParts,
  inputs,
  setInputs,
  answer,
  isCorrect,
  setIsCorrect,
}: {
  codeParts: string[];
  inputs: string[];
  setInputs: Dispatch<SetStateAction<string[]>>;
  answer: string[];
  isCorrect: boolean[];
  setIsCorrect: Dispatch<SetStateAction<boolean[]>>;
}) => {
  type toolTipType = 'success' | 'fail' | 'none';

  const [isShowToolTip, setIsShowToolTip] = useState<toolTipType[]>(
    Array.from({ length: answer.length }, () => 'none')
  );

  const handleInputChange = (index: number, value: string) => {
    if (value.length > answer[index].length) {
      Toast.error('정답보다 입력값이 깁니다.');
      return;
    }

    if (value.charAt(value.length - 1) == ' ') return;

    value = checkAnswer(index, value);

    setInputs(inputs.map((input, i) => (i === index ? value : input)));
  };

  // 정답 확인하는 함수
  const checkAnswer = (idx: number, value: string) => {
    let isSame = true;
    let isAllCorrect = false;

    if (!value.length) {
      return value;
    }

    for (let i = 0; i < value.length; i++) {
      if (value[i] !== answer[idx][i]) {
        isSame = false;
        break;
      }

      if (i === answer[idx].length - 1) {
        isAllCorrect = true;
      }
    }

    if (isSame) {
      let nextIndex = value.length;
      while (nextIndex < answer[idx].length && answer[idx][nextIndex] === ' ') {
        nextIndex++;
        value += ' ';
      }
    }

    console.log(value, answer[idx], isSame);

    if (value !== '' && isSame) {
      if (isAllCorrect) {
        setIsCorrect(isCorrect.map((correct, i) => (idx === i ? true : correct)));
      }

      setIsShowToolTip(isShowToolTip.map((show, i) => (idx === i ? 'success' : show)));

      clearTimeout(timeout[idx]);
      timeout[idx] = setTimeout(() => {
        setIsShowToolTip((prev) => prev.map((show, i) => (idx === i ? 'none' : show)));
      }, 2000);
    } else if (answer[idx].length > value.length || !isSame) {
      setIsShowToolTip(isShowToolTip.map((show, i) => (idx === i ? 'fail' : show)));

      clearTimeout(timeout[idx]);
      timeout[idx] = setTimeout(() => {
        setIsShowToolTip((prev) => prev.map((show, i) => (idx === i ? 'none' : show)));
      }, 2000);
    }
    return value;
  };

  const setTooltip = (idx: number) => {
    switch (isShowToolTip[idx]) {
      case 'none':
        return;
      case 'success':
        return <SuccessTooltip input={inputs[idx]} answer={answer[idx]} />;
      case 'fail':
        return <ErrorTooltip input={inputs[idx]} answer={answer[idx]} />;
    }
  };

  const inCorrectInputClass = 'bg-red-100 text-red-400 border-red-400 animate-shake';
  const correctInputClass = 'outline-blue-700 text-blue-700 bg-blue-200';

  return (
    <pre className="whitespace-pre-wrap break-keep">
      {codeParts.map((part, idx) => (
        <span key={idx}>
          <span>{part}</span>
          {idx < codeParts.length - 1 && (
            <>
              <Tooltip
                id={`tooltip-${idx}`}
                isOpen={isShowToolTip[idx] !== 'none'}
                style={{
                  backgroundColor: 'white',
                  color: 'black',
                  fontFamily: 'Pretendard-Regular',
                }}
                className={`border ${isShowToolTip[idx] === 'success' ? 'border-[#006AFF]' : isShowToolTip[idx] === 'fail' ? 'border-[#FFB9B9]' : 'border-[#6A6A6A]'}`}
              >
                {setTooltip(idx)}
              </Tooltip>
              <div
                className={`bg-MAIN1/20 inline-block my-1 p-1 pl-2 outline-MAIN1 rounded-sm ${inputs[idx] !== '' && isShowToolTip[idx] === 'fail' && inCorrectInputClass} ${isCorrect[idx] && correctInputClass}`}
              >
                <input
                  type="text"
                  value={inputs[idx] || ''}
                  className={` bg-white/0 outline-none`}
                  onChange={(e) => handleInputChange(idx, e.target.value)}
                  style={{ width: `${answer[idx].length * 9}px` }}
                  disabled={isCorrect[idx]}
                  data-tooltip-id={`tooltip-${idx}`}
                />

                {isCorrect[idx] ? (
                  <span className="text-blue-700 text-xs px-[1px]">
                    <Check
                      width={'26px'}
                      height={'26px'}
                      fill="red"
                      style={{ display: 'inline-block' }}
                    />
                  </span>
                ) : (
                  <span className="text-gray-400 text-xs px-[1px]">({answer[idx].length})</span>
                )}
              </div>
            </>
          )}
        </span>
      ))}
    </pre>
  );
};

export default RecodeSolveBox;
