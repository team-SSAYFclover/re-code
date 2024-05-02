import clover from '@/assets/clover.png';
import RecodeList from '@/components/recode/RecodeList';
import { IRecodeItemProps } from '@/pages/recode/RecodePage';
import Modal from '../@common/Modal';

const RecodeListModal = ({ onClose }: { onClose: () => void }) => {
  const TodayReviewData: IRecodeItemProps[] = [
    { problemNo: 3302, problemTitle: 'Job Scheduling', count: 2, solvedYn: true },
    { problemNo: 11779, problemTitle: '최소비용 구하기 2', count: 1, solvedYn: false },
    { problemNo: 1486, problemTitle: '등산', count: 1, solvedYn: true },
    { problemNo: 1261, problemTitle: '알고스팟', count: 2, solvedYn: false },
    { problemNo: 11559, problemTitle: 'Puyo Puyo', count: 2, solvedYn: false },
    { problemNo: 1197, problemTitle: '최소 스패닝 트리', count: 3, solvedYn: false },
    { problemNo: 17471, problemTitle: '게리맨더링', count: 1, solvedYn: false },
  ];

  const solvedCnt = () => {
    return TodayReviewData.filter((x) => x.solvedYn).length;
  };

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
          <div className="w-[50px] text-md">
            {solvedCnt()} / {TodayReviewData.length}
          </div>
        </div>
        <RecodeList review={TodayReviewData} />
      </div>
    </Modal>
  );
};

export default RecodeListModal;
