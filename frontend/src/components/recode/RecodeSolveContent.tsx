import Toast from '@/components/@common/Toast';
import { useRecode } from '@/hooks/recode/useRecode';
import recodeListStore from '@/stores/recodeListStore';
import { IGetRecodeRes } from '@/types/recode';
import { Resizable } from 're-resizable';
import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import MarkdownParser from './MarkdownParser';
import RecodeProgressBar from './RecodeProgressBar';
import RecodeSolveBox from './RecodeSolveBox';

const RecodeSolveContent = ({ recode }: { recode: IGetRecodeRes }) => {
  const params = useParams();
  const navigate = useNavigate();
  const { todayRecodes } = recodeListStore();
  const { usePutRecode } = useRecode();
  const { mutate } = usePutRecode(params.codeId || '');

  const specialChar = '‽▢'; // 특수 문자 설정
  const codeParts = recode.recode.split(specialChar);
  const [inputs, setInputs] = useState<string[]>(Array(codeParts.length - 1).fill('')); // 빈칸 배열
  const [isCorrect, setIsCorrect] = useState<boolean[]>(
    Array.from({ length: recode.answers.length }, () => false)
  );

  const completedCnt = todayRecodes.filter((x) => x.completed).length;

  const resetInput = () => {
    setInputs(
      inputs.map((input, idx) => {
        if (isCorrect[idx]) {
          return input;
        }
        return '';
      })
    );

    Toast.success('모든 빈칸을 초기화했습니다.');
  };

  const getNextIdx = () => {
    let cnt = 0;
    let idx = todayRecodes.findIndex((recode) => recode.codeId === Number(params.codeId)) + 1;

    while (cnt < todayRecodes.length) {
      if (idx === todayRecodes.length) {
        idx = 0;
      }

      if (!todayRecodes[idx].completed) {
        break;
      }

      cnt++;
      idx++;
    }

    return idx;
  };

  const completeRepeat = () => {
    for (const ele of isCorrect) {
      if (!ele) {
        Toast.error('빈칸을 모두 채워야 복습을 완료할 수 있습니다.');
        return;
      }
    }

    mutate(undefined, {
      onSuccess: () => {
        if (completedCnt + 1 === todayRecodes.length) {
          Toast.error('더이상 풀 문제가 없어요.');
          navigate('/recode');
          return;
        } else {
          const idx = getNextIdx();
          navigate(`/recode/${todayRecodes[idx].codeId}`);
        }
      },
    });
  };

  const skip = () => {
    if (!window.confirm('다음 문제로 건너뛰겠습니까?')) {
      return;
    }

    if (completedCnt === todayRecodes.length) {
      Toast.error('더이상 풀 문제가 없어요.');
      return;
    }

    const idx = getNextIdx();
    navigate(`/recode/${todayRecodes[idx].codeId}`);
    Toast.success('다음 문제로 이동했습니다.');
  };

  const btnCommonClass = 'px-3 py-2 rounded-md text-sm shadow-md font-semibold outline-none';

  return (
    <>
      <div className="w-full h-[calc(100vh-120px)] flex">
        <Resizable
          defaultSize={{ width: '50%', height: '100%' }}
          minWidth={'20%'}
          maxWidth={'80%'}
          enable={{
            top: false,
            right: true,
            bottom: false,
            left: false,
            topRight: false,
            bottomRight: false,
            bottomLeft: false,
            topLeft: false,
          }}
          handleStyles={{
            right: {
              width: '15px',
              height: '100%',
              right: '-15px',
              backgroundColor: '#F0F0F0',
              borderRight: '1px solid #d1d5db',
            },
          }}
        >
          <div className="w-full h-full p-4 overflow-y-scroll">
            <MarkdownParser markdown={recode.problemDto.content} />
          </div>
        </Resizable>
        <div className="flex-1 p-4 ml-[15px] overflow-y-scroll bg-gray-100/80">
          <RecodeSolveBox
            inputs={inputs}
            codeParts={codeParts}
            setInputs={setInputs}
            answer={recode.answers}
            isCorrect={isCorrect}
            setIsCorrect={setIsCorrect}
          />
        </div>
      </div>
      <div className="w-full h-16 flex items-center px-4">
        <div className="flex-1 pr-4">
          <RecodeProgressBar total={todayRecodes.length} cnt={completedCnt} />
        </div>
        <div className="flex items-center gap-2 h-10">
          <button
            className={`${btnCommonClass} bg-MAIN2 text-MAIN1 hover:bg-[#D2FFF3] hover:text-[#30CFA8]`}
            onClick={() => skip()}
          >
            건너뛰기
          </button>
          <button
            className={`${btnCommonClass} bg-MAIN2 text-MAIN1  hover:bg-[#D2FFF3] hover:text-[#30CFA8]`}
            onClick={() => resetInput()}
          >
            빈칸 초기화
          </button>
          <button
            className={`${btnCommonClass} text-MAIN2 bg-MAIN1 hover:bg-[#2CD8AE]`}
            onClick={() => completeRepeat()}
          >
            복습 완료하기
          </button>
        </div>
      </div>
    </>
  );
};

export default RecodeSolveContent;
