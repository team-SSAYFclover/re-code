import clover from '@/assets/clover.png';
import RecodeList from '@/components/recode/RecodeList';
import { useRecode } from '@/hooks/recode/useRecode';
import Modal from '../@common/Modal';
import Progressbar from '../@common/Progressbar';

const RecodeListModal = ({ onClose }: { onClose: () => void }) => {
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

  const percentage: number = (solvedCnt() / data.length) * 100;

  return (
    <Modal width="w-1/2" height="h-2/3" onClose={onClose}>
      <div className="w-full h-full mx-auto">
        <div className="h-20 flex items-center">
          <div className="w-[50px] px-2">
            <img src={clover} alt="clover" />
          </div>
          <div className="flex-1">
            <div className="text-xl font-semibold">오늘의 복습 리스트</div>
            <div className="text-sm">문제를 눌러 복습을 시작해보아요.</div>
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
        <div className="h-[calc(100%-108px)]">
          <RecodeList review={data} />
        </div>
      </div>
    </Modal>
  );
};

export default RecodeListModal;
