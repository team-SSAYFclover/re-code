import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

interface IProblemData {
  problemNo: number;
  title: string;
  level: number;
  tags: string[];
  repeatCount: number;
}

let movePosition = 0;
let interval: NodeJS.Timeout | null = null;

const ProblemComp: React.FC<IProblemData> = ({ problemNo, title, level, tags, repeatCount }) => {
  const tagContainerRef = useRef<HTMLDivElement>(null);
  const navigate = useNavigate();
  const [imageSrc, setImageSrc] = useState<string>('');

  useEffect(() => {
    const loadTierImage = async () => {
      try {
        const image = await import(`../../assets/tier/${level}.svg`);
        setImageSrc(image.default);
      } catch (e) {
        console.error('tier 이미지 로드 실패', e);
        import(`@/assets/tier/0.svg`).then((defaultImage) => setImageSrc(defaultImage.default));
      }
    };
    loadTierImage();
  }, [level]);

  const handleMouseOverEvent = () => {
    if (interval) return;
    interval = setInterval(() => {
      tagContainerRef.current?.scrollTo({ left: movePosition, behavior: 'smooth' });
      movePosition += 1;
    }, 10);
  };

  const handleMouseLeaveEvent = () => {
    if (!interval) return;
    clearInterval(interval);
    tagContainerRef.current?.scrollTo({ left: 0 });
    movePosition = 0;
    interval = null;
  };

  return (
    <div
      className="shadow-lg w-72 h-40 me-10 mb-5 p-5 flex flex-col justify-between bg-white rounded-md text-sm cursor-pointer hover:bg-gray-100/50"
      onClick={() => navigate(`/problem/${problemNo}`)}
      onMouseOver={handleMouseOverEvent}
      onMouseLeave={handleMouseLeaveEvent}
    >
      {/* 상단부 */}
      <div className="w-full h-fit flex flex-row justify-between">
        <div>
          <div className="inline rounded-xl w-fit p-1 ps-4 pe-4 m-2 bg-MAIN2 text-MAIN1">BOJ</div>
          <div className="inline text-gray-400">{problemNo}</div>
        </div>
        <div>
          <div className="inline rounded-md text-xs w-fit p-1 ps-3 pe-3 bg-MAIN1 text-MAIN2">
            복습 {repeatCount}회
          </div>
        </div>
      </div>
      {/* 중단부 */}
      <div className="w-full h-2/3 p-2 text-lg font-bold flex">
        <div className="inline h-full me-2 w-5">
          <img src={imageSrc} alt={`level ${level}`} className="inline w-5" />
        </div>
        <div className="inline break-keep w-full flex-grow text-ellipsis overflow-hidden">
          {title}
        </div>
      </div>
      {/* 하단부 */}
      <div
        className="w-full flex flex-row text-xs overflow-x-scroll scrollbar-hide"
        ref={tagContainerRef}
      >
        {tags.map((item) => (
          <div
            key={item}
            className="rounded-md me-2 p-1 ps-2 pe-2 bg-gray-100 text-gray-400 text-nowrap"
          >
            {item}
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProblemComp;
