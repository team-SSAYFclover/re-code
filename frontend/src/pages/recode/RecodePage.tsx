import clover from '@/assets/clover.png';
import NoContent from '@/assets/lotties/noContent.json';
import Progressbar from '@/components/@common/Progressbar';
import Nocontent from '@/components/recode/Nocontent';
import RecodeList from '@/components/recode/RecodeList';
import RecodeSkeleton from '@/components/recode/RecodeSkeleton';
import { useRecode } from '@/hooks/recode/useRecode';
import Lottie from 'react-lottie';
import { useNavigate } from 'react-router-dom';

const RecodePage = () => {
  const defaltOptions = {
    loop: true,
    autoPlay: true,
    animationData: NoContent,
  };

  const navigate = useNavigate();
  const { useGetTodayRecodeList } = useRecode();
  const { data, isLoading } = useGetTodayRecodeList();

  const solvedCnt = () => {
    return data ? data.filter((x) => x.completed).length : 0;
  };

  const handleStartSolve = () => {
    const startCodeId = data ? data.filter((x) => !x.completed)[0].codeId : undefined;
    navigate(`/recode/${startCodeId}`);
  };

  const percentage: number = data ? (solvedCnt() / data.length) * 100 : 0;

  console.log('받아온 데이터', data);

  return (
    <div className="w-full h-full flex justify-center items-center bg-gray-100/50 relative pl-60">
      <div className="w-2/3 h-2/3 min-w-[700px] mx-auto">
        <div className="h-20 flex items-center">
          <div className="w-[56px] px-2">
            <img src={clover} alt="clover" />
          </div>
          <div className="flex-1">
            <div className="text-xl font-semibold">오늘의 복습 리스트</div>
            <div className="text-sm">
              지금까지 쌓인 복습 리스트입니다. 문제를 눌러 복습을 시작해보아요.
            </div>
          </div>
          <div className="w-[180px] text-right">
            {data && data.length > 0 && (
              <>
                <div className=" pr-1 text-md text-right text-gray-500">
                  <span className="text-MAIN1">{data?.length}</span>문제 중&nbsp;
                  <span className="text-MAIN1">{solvedCnt()}</span>문제 복습 완료
                </div>
                <div className="flex justify-center items-center">
                  <Progressbar
                    percentage={data.length === 0 ? 0 : percentage}
                    height="h-2"
                    roundWidth="w-2"
                  />
                  <span className="text-sm text-[#51A1FF]">
                    &nbsp;{Math.floor(percentage)}%&nbsp;
                  </span>
                </div>
              </>
            )}
          </div>
        </div>
        <div className="h-[calc(100%-160px)]">
          {isLoading ? (
            <>
              <RecodeSkeleton />
              <RecodeSkeleton />
              <RecodeSkeleton />
              <RecodeSkeleton />
            </>
          ) : data && data.length > 0 ? (
            <RecodeList review={data} />
          ) : (
            <div className="bg-white rounded-lg w-full h-full ">
              <Nocontent
                text={
                  <>
                    <div className="text-lg">
                      오늘의 복습문제가 없어요 <br />
                      문제를 풀어 복습 문제를 추가해볼까요?
                    </div>
                    <button
                      className="border text-sm border-MAIN1 text-MAIN1 px-4 py-2 mt-2 rounded-md hover:text-white hover:bg-MAIN1"
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
        {!isLoading && data && data.length > 0 && (
          <div className="h-20 flex justify-end items-center">
            <button
              className="h-10 bg-MAIN1 text-MAIN2 px-3 rounded-md hover:bg-[#2CD8AE]"
              onClick={handleStartSolve}
            >
              복습 시작하기
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default RecodePage;
