import clover from '@/assets/clover.png';
import RecodeList from '@/components/recode/RecodeList';
import recodeListStore from '@/stores/recodeListStore';
import Modal from '../../@common/Modal';
import Progressbar from '../../@common/Progressbar';

const RecodeListModal = ({ onClose }: { onClose: () => void }) => {
  const { todayRecodes } = recodeListStore();

  const solvedCnt = () => {
    return todayRecodes.filter((x) => x.completed).length;
  };

  const percentage: number = (solvedCnt() / todayRecodes.length) * 100;

  return (
    <Modal width="w-1/2" height="h-2/3" onClose={onClose}>
      <div className="w-full h-full mx-auto">
        <div className="h-20 flex items-center">
          <div className="w-[56px] px-2">
            <img src={clover} alt="clover" />
          </div>
          <div className="flex-1">
            <div className="text-xl font-semibold">오늘의 복습 리스트</div>
            <div className="text-sm">문제를 눌러 복습을 시작해보아요.</div>
          </div>
          <div className="w-[180px] text-right">
            <div className=" pr-1 text-md text-right text-gray-500">
              <span className="text-MAIN1">{todayRecodes.length}</span>문제 중&nbsp;
              <span className="text-MAIN1">{solvedCnt()}</span>문제 복습 완료
            </div>
            <div className="flex justify-center items-center">
              <Progressbar
                percentage={todayRecodes.length === 0 ? 0 : percentage}
                height="h-2"
                roundWidth="w-2"
              />
              <span className="text-sm text-[#51A1FF]">
                &nbsp;{todayRecodes.length === 0 ? 0 : Math.floor(percentage)}%&nbsp;
              </span>
            </div>
          </div>
        </div>
        <div className="h-[calc(100%-108px)]">
          {todayRecodes.length === 0 ? (
            <div className="w-full h-full flex justify-center items-center">
              오늘의 복습 리스트가 없습니다.
            </div>
          ) : (
            <RecodeList review={todayRecodes} />
          )}
        </div>
      </div>
    </Modal>
  );
};

export default RecodeListModal;
