import { ReactComponent as Tear } from '@/assets/tear.svg';
import { IGetRecodeRes } from '@/types/recode';
import { useEffect, useRef, useState } from 'react';
import { FaCode } from 'react-icons/fa6';
import { PiNoteDuotone } from 'react-icons/pi';
import { useLocation } from 'react-router-dom';
import RecodeCodeModal from './Modal/RecodeCodeModal';
import RecodeListModal from './Modal/RecodeListModal';

const RecodeHeader = ({ recode }: { recode: IGetRecodeRes }) => {
  const [isShowListModal, setIsShowListModal] = useState<boolean>(false);
  const [isShowCodeModal, setIsShowCodeModal] = useState<boolean>(false);

  const location = useLocation();
  const prevLocation = useRef(location);

  useEffect(() => {
    if (prevLocation.current.pathname !== location.pathname) {
      setIsShowListModal(false);
      setIsShowCodeModal(false);

      prevLocation.current = location;
    }
  }, [location]);

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
          <Tear width={28} height={28} fill={setTearColor(recode.problemDto.level)} />
          <span className="absolute bottom-[6px] left-0 right-[1px] text-white text-md font-semibold">
            {getLevel(recode.problemDto.level)}
          </span>
        </div>
        <div className="text-lg pl-2 font-semibold">{recode.problemDto.title}</div>
      </div>
      <div className="flex">
        <button
          className="w-32 h-10 mr-2 flex justify-center items-center font-semibold bg-gray-100/50 text-gray-400 text-sm rounded-md shadow-md hover:bg-gray-100/90 hover:text-gray-600 outline-none"
          onClick={() => setIsShowCodeModal(true)}
        >
          <FaCode size={20} />
          &nbsp;기존 코드
        </button>
        <button
          className="w-32 h-10 flex justify-center items-center font-semibold bg-MAIN2 text-MAIN1 text-sm rounded-md shadow-md hover:bg-[#D2FFF3] hover:text-[#30CFA8] outline-none"
          onClick={() => setIsShowListModal(true)}
        >
          <PiNoteDuotone size={20} />
          &nbsp;문제 리스트
        </button>
      </div>
      {isShowListModal && <RecodeListModal onClose={() => setIsShowListModal(false)} />}
      {isShowCodeModal && (
        <RecodeCodeModal code={recode.recode} onClose={() => setIsShowCodeModal(false)} />
      )}
    </div>
  );
};

export default RecodeHeader;
