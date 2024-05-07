import recodeListStore from '@/stores/recodeListStore';
import { IGetRecodeRes } from '@/types/recode';
import { Resizable } from 're-resizable';
import { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Toast from '../@common/Toast';
import MarkdownParser from './MarkdownParser';
import RecodeProgressBar from './RecodeProgressBar';
import RecodeSolveBox from './RecodeSolveBox';

const RecodeSolveContent = ({ recode }: { recode: IGetRecodeRes }) => {
  const params = useParams();
  const navigate = useNavigate();
  const { todayRecodes } = recodeListStore();
  const specialChar = '‽▢'; // 특수 문자 설정
  const codeParts = recode.recode.split(specialChar);
  const [inputs, setInputs] = useState<string[]>(Array(codeParts.length - 1).fill('')); // 빈칸 배열

  const completedCnt = todayRecodes.filter((x) => x.completed).length;

  const handleInputChange = (index: number, value: string) => {
    setInputs(inputs.map((input, i) => (i === index ? value : input)));
  };

  const resetInput = () => {
    setInputs(inputs.map(() => ''));
  };

  const completeRepeat = () => {
    // @TODO: 복습 완료 api 호출
  };

  const skip = () => {
    // @TODO: 건너뛰기 구현
    if (completedCnt === todayRecodes.length) {
      Toast.error('더이상 풀 문제가 없어요.');
      return;
    }

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

    navigate(`/recode/${todayRecodes[idx].codeId}`);
  };

  const btnCommonClass = 'px-3 py-2 rounded-md text-sm shadow-md font-semibold';

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
            <MarkdownParser markdown={recode.problem.content} />
          </div>
        </Resizable>
        <div className="flex-1 p-4 ml-[15px] overflow-y-scroll">
          <RecodeSolveBox
            inputs={inputs}
            codeParts={codeParts}
            handleInputChange={handleInputChange}
            answer={recode.answers}
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
