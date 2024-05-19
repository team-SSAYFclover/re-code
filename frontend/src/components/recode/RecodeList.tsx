import { IProblemInfo } from '@/types/model';
import RecodeItem from './RecodeItem';

const RecodeList = ({ review }: { review: IProblemInfo[] }) => {
  return (
    <div className="h-full overflow-y-scroll overflow-x-hidden">
      {review.map((item) => (
        <RecodeItem
          key={item.codeId}
          problemNo={item.problemNo}
          codeId={item.codeId}
          title={item.title}
          reviewCnt={item.reviewCnt}
          completed={item.completed}
        />
      ))}
    </div>
  );
};

export default RecodeList;
