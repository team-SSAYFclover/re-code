import React, { useEffect, useState } from 'react';
import { IProblemData } from './ProblemContent';
import { useNavigate } from 'react-router-dom';

const ProblemComp: React.FC<IProblemData> = ({ problemNo, title, level, tagName, repeatNum }) => {
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

  return (
    <div
      className="shadow-lg w-72 h-40 me-10 mb-5 p-5 flex flex-col justify-between bg-white rounded-md text-sm cursor-pointer"
      onClick={() => navigate(`/problem/${problemNo}`)}
    >
      {/* 상단부 */}
      <div className="w-full h-fit flex flex-row justify-between">
        <div>
          <div className="inline rounded-xl w-fit p-1 ps-4 pe-4 m-2 bg-MAIN2 text-MAIN1">BOJ</div>
          <div className="inline text-gray-400">{problemNo}</div>
        </div>
        <div>
          <div className="inline rounded-md text-xs w-fit p-1 ps-3 pe-3 bg-MAIN1 text-MAIN2">
            복습 {repeatNum}회
          </div>
        </div>
      </div>
      {/* 중단부 */}
      <div className="w-full h-2/3 p-2 text-lg font-bold">
        <img src={imageSrc} alt={`level ${level}`} className="inline w-5 me-2" />
        <div className="inline">{title}</div>
      </div>
      {/* 하단부 */}
      <div className="w-full flex flex-row text-xs overflow-hidden">
        {tagName.map((item) => (
          <div key={item} className="rounded-md me-2 p-1 ps-2 pe-2 bg-gray-100 text-gray-400">
            {item}
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProblemComp;
