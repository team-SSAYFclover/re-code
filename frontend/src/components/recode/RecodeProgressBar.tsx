import clover from '@/assets/clover.png';
import Progressbar from '@/components/@common/Progressbar';

const RecodeProgressBar = ({ total, cnt }: { total: number; cnt: number }) => {
  const percentage: number = (cnt / total) * 100;

  return (
    <>
      <div className="pb-1 flex relative">
        <div style={{ width: `${percentage}%` }} className="transition-all duration-500 ease-out" />
        <img src={clover} alt="clover" width={24} className="relative left-[-16px]" />
      </div>
      <Progressbar percentage={total === 0 ? 0 : percentage} roundWidth="w-2" height="h-2" />
      <span className="text-md text-gray-700">
        오늘 복습 문제 <span className="text-MAIN1">{total}</span>문제 중{' '}
        <span className="text-MAIN1">{cnt}</span>문제 완료
      </span>
    </>
  );
};

export default RecodeProgressBar;
