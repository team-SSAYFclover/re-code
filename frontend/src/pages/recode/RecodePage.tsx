import clover from '@/assets/clover.png';
import Progressbar from '@/components/@common/Progressbar';
import RecodeList from '@/components/recode/RecodeList';
import { useRecode } from '@/hooks/recode/useRecode';
import { useNavigate } from 'react-router-dom';

const RecodePage = () => {
  const navigate = useNavigate();
  const { useGetTodayRecodeList } = useRecode();
  const { data, isLoading } = useGetTodayRecodeList();

  if (isLoading) {
    return <div>로딩 중</div>;
  }

  if (!data) {
    return <div>데이터 없음</div>;
  }

  const solvedCnt = () => {
    return data.filter((x) => x.completed).length;
  };

  const handleStartSolve = () => {
    const startCodeId = data.filter((x) => !x.completed)[0].codeId;
    navigate(`/recode/${startCodeId}`);
  };

  const percentage: number = (solvedCnt() / data.length) * 100;

  return (
    <div className="w-full h-full flex justify-center items-center bg-gray-100/50 relative">
      <div className="w-2/3 h-2/3 mx-auto">
        <div className="h-20 flex items-center">
          <div className="w-[50px] px-2">
            <img src={clover} alt="clover" />
          </div>
          <div className="flex-1">
            <div className="text-xl font-semibold">오늘의 복습 리스트</div>
            <div className="text-sm">
              지금까지 쌓인 복습 리스트입니다. 문제를 눌러 복습을 시작해보아요.
            </div>
          </div>
          <div className="w-[180px] text-right">
            <div className=" pr-1 text-md text-right text-gray-500">
              <span className="text-MAIN1">{data.length}</span>문제 중&nbsp;
              <span className="text-MAIN1">{solvedCnt()}</span>문제 복습 완료
            </div>
            <div className="flex justify-center items-center">
              <Progressbar percentage={percentage} height="h-2" roundWidth="w-2" />
              <span className="text-sm text-[#51A1FF]">&nbsp;{Math.floor(percentage)}%&nbsp;</span>
            </div>
          </div>
        </div>
        <div className="h-[calc(100%-160px)]">
          <RecodeList review={data} />
        </div>
        <div className="h-20 flex justify-end items-center">
          <button
            className="h-10 bg-MAIN1 text-MAIN2 px-3 rounded-md hover:bg-[#2CD8AE]"
            onClick={handleStartSolve}
          >
            복습 시작하기
          </button>
        </div>
      </div>
    </div>
  );
};

export default RecodePage;
