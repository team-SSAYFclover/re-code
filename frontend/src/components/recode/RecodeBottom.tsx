import { IGetProblemRes } from '@/pages/recode/RecodeDetailPage';
import RecodeProgressBar from './RecodeProgressBar';

const RecodeBottom = ({ recode }: { recode: IGetProblemRes }) => {
  const btnCommonClass = 'px-3 py-2 rounded-md text-sm shadow-md font-semibold';

  return (
    <div className="w-full h-16 flex items-center px-4">
      <div className="flex-1 pr-4">
        <RecodeProgressBar total={10} cnt={3} />
      </div>
      <div className="flex items-center gap-2 h-10">
        <button
          className={`${btnCommonClass} bg-MAIN2 text-MAIN1 hover:bg-[#D2FFF3] hover:text-[#30CFA8]`}
        >
          건너뛰기
        </button>
        <button
          className={`${btnCommonClass} bg-MAIN2 text-MAIN1  hover:bg-[#D2FFF3] hover:text-[#30CFA8]`}
        >
          빈칸 초기화
        </button>
        <button className={`${btnCommonClass} text-MAIN2 bg-MAIN1 hover:bg-[#2CD8AE]`}>
          복습 완료하기
        </button>
      </div>
    </div>
  );
};

export default RecodeBottom;
