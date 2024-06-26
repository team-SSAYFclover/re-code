import {
  useAddReview,
  useDeleteCode,
  usePatchCode,
  useProbDetail,
} from '@/hooks/problem/useProblem';
import { ICodeResList, IProbDetailInfo } from '@/types/problem';
import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react';
import { useParams } from 'react-router';
import { useNavigate } from 'react-router-dom';
import Toast from '../@common/Toast';
import MarkdownParser from './MarkdownParser';
import ProblemDetailCodeComp from './ProblemDetailCodeComp';

const ProblemDetailContent: React.FC = () => {
  const { useGetProbDetail } = useProbDetail();
  const { mutate: deleteCodeMutate } = useDeleteCode();
  const { mutate: patchCodeMutate } = usePatchCode();
  const { mutate: addReviewMutate } = useAddReview();
  const params = useParams();
  const navigate = useNavigate();
  const [imageSrc, setImageSrc] = useState<string>('');
  const codesRef = useRef<HTMLDivElement | null>(null);
  const containerRef = useRef<HTMLDivElement | null>(null);
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
          const params = { name: codeResLists.name, reviewStatus: !codeResLists.reviewStatus };
          patchCodeMutate({ codeId: id, params });
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

  const updateCodeName = (id: number, newName: string) => {
    setProblemData((prevData) => {
      const updatedCodes = prevData.codeResLists.map((codeResLists) => {
        if (codeResLists.id === id) {
          const params = { name: newName, reviewStatus: codeResLists.reviewStatus };
          patchCodeMutate({ codeId: id, params });
          return { ...codeResLists, name: newName };
        }
        return codeResLists;
      });
      return { ...prevData, codeResLists: updatedCodes };
    });
  };

  const addTodayReview = (codeId: number) => {
    addReviewMutate(codeId, {
      onError: (error: Error) => {
        if (axios.isAxiosError(error) && error.response?.status === 409) {
          Toast.error('이미 복습리스트에 추가된 문제입니다.');
          return;
        }

        Toast.error('추가에 실패했어요.');
      },
    });
  };

  const handleMoveEvent = () => {
    containerRef.current?.scrollBy({
      top: codesRef.current?.getBoundingClientRect().top,
      behavior: 'smooth',
    });
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

  return (
    <div className="w-full h-full py-5 px-20 flex flex-col overflow-auto" ref={containerRef}>
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
          <div className="flex gap-3">
            <div className="inline rounded-md text-xs w-fit p-1 ps-3 pe-3 bg-MAIN1 text-MAIN2">
              복습 {problemData.reviewCount}회
            </div>
            <div
              className="inline rounded-md text-xs w-fit p-1 ps-3 pe-3 bg-gray-300 text-white hover:cursor-pointer"
              onClick={handleMoveEvent}
            >
              {`< / >`}
            </div>
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
      <div className="mt-10 mb-10">
        <MarkdownParser markdown={problemData.content} />
      </div>
      {/* 하단부 : 코드 내역 */}
      <div className="w-full h-fit" ref={codesRef}>
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
            onModifyName={updateCodeName}
            addReview={() => addTodayReview(item.id)}
          />
        ))}
      </div>
    </div>
  );
};

export default ProblemDetailContent;
