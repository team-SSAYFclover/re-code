import { ReactComponent as Tear } from '@/assets/tear.svg';
import { IGetProblemRes } from '@/pages/recode/RecodeDetailPage';
import { PiNoteDuotone } from 'react-icons/pi';

const RecodeHeader = ({ recode }: { recode: IGetProblemRes }) => {
  const setTearColor = (tear: number): string => {
    if (tear <= 5) return '#ad5600';
    if (tear <= 10) return '#435f7a';
    if (tear <= 15) return '#ec9a00';
    if (tear <= 20) return '#75e4ae';
    if (tear <= 25) return '#00b4fc';
    return '#ff0062';
  };

  const getLevel = (tear: number) => {
    return tear % 5 === 0 ? 1 : 6 - (tear % 5);
  };

  return (
    <div className="w-full h-14 px-4 flex justify-around items-center border-b-[1px]">
      <div className="w-full flex items-center">
        <div className="w-7 h-7 relative text-center">
          <Tear width={28} height={28} fill={setTearColor(recode.level)} />
          <span className="absolute bottom-[6px] left-0 right-[1px] text-white text-md font-semibold">
            {getLevel(recode.level)}
          </span>
        </div>
        <div className="text-lg pl-2 font-semibold">{recode.title}</div>
      </div>
      <div>
        <button className="w-32 h-10 flex justify-center items-center font-semibold bg-MAIN2 text-MAIN1 text-sm rounded-md shadow-md hover:bg-[#D2FFF3] hover:text-[#30CFA8]">
          <PiNoteDuotone size={20} />
          &nbsp;문제 리스트
        </button>
      </div>
    </div>
  );
};

export default RecodeHeader;
