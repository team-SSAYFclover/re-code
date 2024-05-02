import { IRecodeItemProps } from '@/pages/recode/RecodePage';
import { useNavigate } from 'react-router-dom';

const RecodeItem = ({ problemNo, problemTitle, count, solvedYn }: IRecodeItemProps) => {
  const navigate = useNavigate();

  return (
    <div
      className={`w-full h-fit p-4 mb-2 rounded-lg flex-row ${solvedYn ? 'bg-gray-200/80' : 'bg-white hover:bg-gray-100 cursor-pointer'} shadow-inner`}
      //   @TODO: 나중에 코드 번호로 바꾸기
      onClick={() => navigate(`/recode/1`)}
    >
      <div className="flex justify-between">
        <div className="text-sm font-semibold text-gray-500">BOJ {problemNo}</div>
        <div className="w-fit p-1 bg-MAIN2 text-MAIN1 rounded-md text-sm text-center">
          {count}번째 복습
        </div>
      </div>
      <div className="text-md font-semibold">{problemTitle}</div>
    </div>
  );
};

export default RecodeItem;
