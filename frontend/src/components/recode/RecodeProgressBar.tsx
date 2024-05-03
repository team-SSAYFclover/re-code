import clover from '@/assets/clover.png';

const RecodeProgressBar = ({ total, cnt }: { total: number; cnt: number }) => {
  const percentage: number = (cnt / total) * 100;

  return (
    <>
      <div className="pb-1 flex relative">
        <div style={{ width: `${percentage}%` }} className="transition-all duration-500 ease-out" />
        <img src={clover} alt="clover" width={24} className="relative left-[-16px]" />
      </div>
      <div className="w-full bg-[#F0F0F0] h-2 rounded-full">
        <div
          className={`bg-gradient-to-r from-MAIN1 to-[#51A1FF] h-2 transition-all duration-500 ease-out rounded-full relative`}
          style={{ width: `${percentage}%` }}
        >
          <div className={`bg-[#217FEE] w-2 h-2 rounded-full absolute right-0`} />
        </div>
      </div>
      <span className="text-md text-gray-700">
        오늘 복습 문제 <span className="text-MAIN1">{total}</span>문제 중{' '}
        <span className="text-MAIN1">{cnt}</span>문제 완료
      </span>
    </>
  );
};

export default RecodeProgressBar;
