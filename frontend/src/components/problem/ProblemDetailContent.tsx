import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import ProblemDetailCodeComp, { ICode } from './ProblemDetailCodeComp';

interface IProblemData {
  problemNo: number;
  title: string;
  level: number;
  content: string;
  tagName: string[];
  repeatNum: number;
  code: ICode[];
}

const ProblemDetailContent: React.FC = () => {
  const params = useParams();
  const [imageSrc, setImageSrc] = useState<string>('');
  const [problemData, setProblemData] = useState<IProblemData>({
    problemNo: parseInt(params.problemNo ?? '-1', 10),
    title: '',
    level: -1,
    content: '',
    tagName: [],
    repeatNum: 0,
    code: [],
  });

  useEffect(() => {
    setProblemData((prevData) => ({
      ...prevData,
      title: '알고스팟',
      level: 12,
      content:
        '알고스팟 운영진이 모두 미로에 갇혔다. 미로는 N*M 크기이며, 총 1*1크기의 방으로 이루어져 있다. 미로는 빈 방 또는 벽으로 이루어져 있고, 빈 방은 자유롭게 다닐 수 있지만, 벽은 부수지 않으면 이동할 수 없다. \n\n 알고스팟 운영진은 여러명이지만, 항상 모두 같은 방에 있어야 한다. 즉, 여러 명이 다른 방에 있을 수는 없다. 어떤 방에서 이동할 수 있는 방은 상하좌우로 인접한 빈 방이다. 즉, 현재 운영진이 (x, y)에 있을 때, 이동할 수 있는 방은 (x+1, y), (x, y+1), (x-1, y), (x, y-1) 이다. 단, 미로의 밖으로 이동 할 수는 없다. \n\n 벽은 평소에는 이동할 수 없지만, 알고스팟의 무기 AOJ를 이용해 벽을 부수어 버릴 수 있다. \n\n 벽을 부수면, 빈 방과 동일한 방으로 변한다. 만약 이 문제가 알고스팟에 있다면, 운영진들은 궁극의 무기 sudo를 이용해 벽을 한 번에 다 없애버릴 수 있지만, 안타깝게도 이 문제는 Baekjoon Online Judge에 수록되어 있기 때문에, sudo를 사용할 수 없다. \n\n 현재 (1, 1)에 있는 알고스팟 운영진이 (N, M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 구하는 프로그램을 작성하시오.',
      tagName: ['다익스트라', '최단경로'],
      repeatNum: 2,
      code: [
        {
          id: 1,
          name: '240501 다익스트라로 풀었음',
          content: '다익스트라코드',
          reviewStatus: true,
          date: '24-05-01',
        },
        {
          id: 2,
          name: '240502 플로이드워샬로 풀었음',
          content: '플로이드워샬코드',
          reviewStatus: false,
          date: '24-05-02',
        },
      ],
    }));

    const loadTierImage = async () => {
      try {
        const image = await import(`../../assets/tier/${problemData.level}.svg`);
        setImageSrc(image.default);
      } catch (e) {
        console.error('tier 이미지 로드 실패', e);
        import(`@/assets/tier/0.svg`).then((defaultImage) => setImageSrc(defaultImage.default));
      }
    };
    loadTierImage();
  }, [problemData.level]);

  return (
    <div className="w-full h-full pt-5 pe-20 flex flex-col overflow-auto">
      {/* 상단부 : 문제 정보 */}
      <div className="w-full h-fit flex flex-col">
        {/* 번호 */}
        <div className="w-full h-fit flex flex-row">
          <div className="inline rounded-xl w-fit p-0.5 ps-4 pe-4 me-2 text-sm bg-MAIN2 text-MAIN1">
            BOJ
          </div>
          <div className="inline pt-auto pb-auto text-gray-400">{problemData.problemNo}</div>
        </div>
        {/* 제목 */}
        <div className="w-full h-fit m-2 flex flex-row">
          <img src={imageSrc} alt={`level ${problemData.level}`} className="inline w-7 me-2" />
          <div className="inline text-3xl font-bold">{problemData.title}</div>
        </div>
        <div className="w-full h-0.5 mb-1 bg-gray-100"></div>
        {/* 복습횟수, 태그 */}
        <div className="w-full h-fit flex flex-row justify-between">
          <div className="inline rounded-md text-xs w-fit p-1 ps-3 pe-3 bg-MAIN1 text-MAIN2">
            복습 {problemData.repeatNum}회
          </div>
          <div className="h-fit flex flex-row text-xs overflow-hidden">
            {problemData.tagName.map((item) => (
              <div key={item} className="rounded-md ms-2 p-1 ps-2 pe-2 bg-gray-100 text-gray-400">
                {item}
              </div>
            ))}
          </div>
        </div>
      </div>
      {/* 중단부 : 문제 내용 */}
      <div className="mt-10 mb-10 whitespace-pre-wrap">{problemData.content}</div>
      {/* 하단부 : 코드 내역 */}
      <div className="w-full h-fit">
        <div className="w-full h-0.5 mb-3 bg-gray-100"></div>
        {problemData.code.map((item: ICode, index: number) => (
          <ProblemDetailCodeComp
            key={index}
            id={item.id}
            name={item.name}
            content={item.content}
            reviewStatus={item.reviewStatus}
            date={item.date}
          />
        ))}
      </div>
    </div>
  );
};

export default ProblemDetailContent;
