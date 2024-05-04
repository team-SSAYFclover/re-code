import { IProblemInfo } from '@/types/model';
import RecodeItem from './RecodeItem';

const RecodeList = ({ review }: { review: IProblemInfo[] }) => {
  return (
    <div className="h-full overflow-y-scroll overflow-x-hidden">
      {review.map((item) => (
        <RecodeItem
          key={item.problemId}
          problemId={item.problemId}
          codeId={item.codeId}
          name={item.name}
          reviewCnt={item.reviewCnt}
          completed={item.completed}
        />
      ))}
    </div>
  );
};

export default RecodeList;
