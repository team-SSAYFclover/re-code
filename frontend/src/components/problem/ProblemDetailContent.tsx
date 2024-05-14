import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { marked } from 'marked';
import { useNavigate } from 'react-router-dom';
import ProblemDetailCodeComp from './ProblemDetailCodeComp';
import { IProbDetailInfo, ICodeResList } from '@/types/problem';
import { useProbDetail, useDeleteCode } from '@/hooks/problem/useProblem';

const ProblemDetailContent: React.FC = () => {
  const { useGetProbDetail } = useProbDetail();
  const { mutate: deleteCodeMutate } = useDeleteCode();
  const params = useParams();
  const navigate = useNavigate();
  const [imageSrc, setImageSrc] = useState<string>('');
  const [problemData, setProblemData] = useState<IProbDetailInfo>({
    id: -1,
    problemNo: parseInt(params.problemNo ?? '-1', 10),
    title: '',
    level: -1,
    reviewCount: 0,
    tags: [],
    content: '',
    codeResLists: [],
  });

  const toggleReviewStatus = (id: number) => {
    setProblemData((prevData) => {
      const updatedCodes = prevData.codeResLists.map((codeResLists) => {
        if (codeResLists.id === id) {
          // 코드 토글 api 호출
          return { ...codeResLists, reviewStatus: !codeResLists.reviewStatus };
        }
        return codeResLists;
      });
      return { ...prevData, codeResLists: updatedCodes };
    });
  };

  const deleteCode = (id: number) => {
    deleteCodeMutate(id, {
      onSuccess: () => {
        setProblemData((prevData) => ({
          ...prevData,
          codeResLists: prevData.codeResLists.filter((codeResLists) => codeResLists.id !== id),
        }));
      },
      onError: (error) => {
        console.error('코드 삭제 에러', error);
      },
    });

    if (problemData.codeResLists.length == 0) {
      navigate('/problem');
    }
  };

  const { data } = useGetProbDetail(problemData.problemNo);
  useEffect(() => {
    if (data) {
      setProblemData({
        ...data,
      });
    }

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
  }, [data, problemData.level]);

  const renderMarkdown = (markdownText: string) => {
    const rawMarkup = marked.parse(markdownText);
    return { __html: rawMarkup };
  };

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
            복습 {problemData.reviewCount}회
          </div>
          <div className="h-fit flex flex-row text-xs overflow-hidden">
            {problemData.tags.map((item) => (
              <div key={item} className="rounded-md ms-2 p-1 ps-2 pe-2 bg-gray-100 text-gray-400">
                {item}
              </div>
            ))}
          </div>
        </div>
      </div>
      {/* 중단부 : 문제 내용 */}
      <div
        className="mt-10 mb-10 whitespace-pre-wrap"
        dangerouslySetInnerHTML={renderMarkdown(problemData.content)}
      ></div>
      {/* 하단부 : 코드 내역 */}
      <div className="w-full h-fit">
        <div className="w-full h-0.5 mb-3 bg-gray-100"></div>
        {problemData.codeResLists.map((item: ICodeResList) => (
          <ProblemDetailCodeComp
            key={item.id}
            id={item.id}
            name={item.name}
            content={item.content}
            reviewStatus={item.reviewStatus}
            date={item.submitTime}
            toggleReviewStatus={() => toggleReviewStatus(item.id)}
            deleteCode={() => deleteCode(item.id)}
          />
        ))}
      </div>
    </div>
  );
};

export default ProblemDetailContent;
