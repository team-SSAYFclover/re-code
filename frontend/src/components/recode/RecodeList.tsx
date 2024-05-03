import { IRecodeItemProps } from '@/pages/recode/RecodePage';
import RecodeItem from './RecodeItem';

const RecodeList = ({ review }: { review: IRecodeItemProps[] }) => {
  return (
    <div className="h-[calc(100%-160px)] overflow-y-scroll overflow-x-hidden">
      {review.map((item) => (
        <RecodeItem
          key={item.problemNo}
          problemNo={item.problemNo}
          problemTitle={item.problemTitle}
          count={item.count}
          solvedYn={item.solvedYn}
        />
      ))}
    </div>
  );
};

export default RecodeList;
