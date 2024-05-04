import { IProblemInfo } from '@/types/model';
import { useNavigate } from 'react-router-dom';

const RecodeItem = ({ problemId, codeId, name, reviewCnt, completed }: IProblemInfo) => {
  const navigate = useNavigate();

  return (
    <div
      className={`w-full h-fit p-4 mb-3 rounded-lg flex-row shadow-md bg-white ${completed ? 'text-[#51A1FF]' : 'bg-white hover:bg-gray-100 cursor-pointer'}`}
      onClick={() => {
        if (completed) return;
        navigate(`/recode/${codeId}`);
      }}
    >
      <div className="flex justify-between">
        <div className={`text-sm text-gray-500 ${completed && 'text-[#51A1FF]'}`}>
          BOJ {problemId}
        </div>
        <div className="w-fit p-1 bg-MAIN2 text-MAIN1 rounded-md text-sm text-center">
          {reviewCnt}번째 복습
        </div>
      </div>
      <div className="text-md">
        {name}
        {completed && (
          <span className="bg-blue-400 text-white px-1.5 py-0.5 ml-2 rounded-md text-[12px]">
            복습 완료
          </span>
        )}
      </div>
    </div>
  );
};

export default RecodeItem;
