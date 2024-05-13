import clover0 from '@/assets/clover/0.png';
import clover1 from '@/assets/clover/1.png';
import clover2 from '@/assets/clover/2.png';
import clover3 from '@/assets/clover/3.png';
import clover4 from '@/assets/clover/4.png';
import style1 from '@/assets/clover/DaySky.module.css';
import style2 from '@/assets/clover/TwilightSky.module.css';
import React from 'react';
import NightSky from './cloverbgComp/NightSky';

interface CloverCompProps {
  leafNum: number;
}

const CloverComp: React.FC<CloverCompProps> = ({ leafNum }) => {
  const nightBG = {
    background: 'linear-gradient(135deg, #3351B3, #1A206F, #391761)',
  };
  // 현재 시간 받아오기
  const hour = new Date().getHours();

  const cloverImages: { [key: number]: string } = {
    0: clover0,
    1: clover1,
    2: clover2,
    3: clover3,
    4: clover4,
  };

  // props로 받은 leafNum에 따라 이미지 선택
  const cloverImage = cloverImages[leafNum];

  return (
    <div className="shadow-lg h-[40%] flex justify-center relative bg-white rounded-lg overflow-hidden mb-[10px]">
      {/* 시간에 따라 다른 배경 컴포넌트 렌더링 */}
      {hour >= 21 || hour < 4 ? (
        <div className="h-full w-full absolute">
          <div className="w-full h-3/4 absolute top-0 overflow-hidden" style={nightBG}>
            <NightSky />
          </div>
          <div className="w-full h-1/4 absolute bottom-0 bg-gradient-to-t from-orange-50 to-orange-100"></div>
        </div>
      ) : hour >= 7 && hour < 17 ? (
        <div className="h-full w-full absolute">
          <div
            className={style1.daysky}
            style={{ width: '100%', height: '75%', position: 'absolute' }}
          ></div>
          <div className="w-full h-1/4 absolute bottom-0 bg-gradient-to-t from-orange-50 to-orange-100"></div>
        </div>
      ) : (
        <div className="h-full w-full absolute">
          <div
            className={style2.twilightsky}
            style={{ width: '100%', height: '75%', position: 'absolute' }}
          ></div>
          <div className="w-full h-1/4 absolute bottom-0 bg-gradient-to-t from-rose-50 to-rose-200"></div>
        </div>
      )}
      <img
        src={cloverImage}
        alt={`clover_${leafNum}`}
        className="z-10 animate-wiggle origin-bottom-center"
      />
    </div>
  );
};

export default CloverComp;
