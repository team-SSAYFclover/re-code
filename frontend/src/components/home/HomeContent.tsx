import { useHome } from '@/hooks/home/useHome';
import userStore from '@/stores/userStore';
import React from 'react';
import ErrorContent from '../@common/ErrorContent';
import LoadingContent from '../@common/LoadingContent';
import AttendCheckComp from './AttendCheckComp';
import CloverComp from './CloverComp';
import OctagonGraphComp from './OctagonGraphComp';
import RecommendComp from './RecommendComp';
import TodayReviewComp from './TodayReviewComp';
import WeekReviewComp from './WeekReviewComp';

const HomeContent: React.FC = () => {
  const { name } = userStore();
  const { useGetMainInfo } = useHome();
  const { data, isLoading } = useGetMainInfo();
  // console.log('가져온 데이터 : ', data);
  // API에서 받은 weekReviews 객체를 배열로 변환
  const weekRepeatData = data
    ? [
        { date: '월', 복습량: data.weekReviews.mon },
        { date: '화', 복습량: data.weekReviews.tue },
        { date: '수', 복습량: data.weekReviews.wed },
        { date: '목', 복습량: data.weekReviews.thu },
        { date: '금', 복습량: data.weekReviews.fri },
        { date: '토', 복습량: data.weekReviews.sat },
        { date: '일', 복습량: data.weekReviews.sun },
      ]
    : [];

  const OctagonData = data
    ? Object.keys(data.algoReview).map((key) => ({
        name: key,
        복습량: data.algoReview[key],
      }))
    : [];

  const TodayReviewData = data
    ? data.todayProblems.map((problem) => ({
        codeId: problem.codeId,
        problemNo: problem.problemNo,
        problemTitle: problem.title,
        count: problem.reviewCnt,
        solvedYn: problem.completed,
      }))
    : [];

  const solvedCount = TodayReviewData.filter((problem) => problem.solvedYn).length;
  const totalProblems = TodayReviewData.length;
  const leaf = totalProblems > 0 ? Math.floor((solvedCount / totalProblems) * 4) : 0;

  const suppleProb = data
    ? { problemNo: data.supplementaryQuestion, problemTitle: data.supplementaryTitle }
    : { problemNo: 3302, problemTitle: 'Jop Scheduling' };
  const randProb = data
    ? { problemNo: data.randomQuestion, problemTitle: data.randomTitle }
    : { problemNo: 11779, problemTitle: '최소비용 구하기 2' };

  if (isLoading) return <LoadingContent />;
  if (!data) return <ErrorContent />;

  return (
    <div className="w-full h-full pb-8">
      {/* 상단 인사부분 */}
      <div className="h-[60px]">
        <div className="text-lg">
          <span className="font-semibold">{name}</span>님 복습{' '}
          <span className="font-semibold">
            {' '}
            연속 <span className="text-MAIN1">{data.sequence}</span>일째
          </span>
          예요!
        </div>
        <div className="text-gray-400 text-sm">
          <span className="text-MAIN1 font-semibold">re:code</span>에서 함께 알고리즘 복습 해볼까요?
        </div>
      </div>
      <div className="w-full h-[calc(100%-60px)] flex flex-row justify-between">
        {/* 좌측 */}
        <div className="w-3/5 mr-6 h-full flex flex-col justify-between">
          <AttendCheckComp weekRepeatData={weekRepeatData} />
          <WeekReviewComp weekRepeatData={weekRepeatData} percentage={data.ranking} />
          {/* 좌측 하단 */}
          <div className="h-[35%] flex justify-between">
            <OctagonGraphComp OctagonData={OctagonData} />
            <RecommendComp suppleProb={suppleProb} randProb={randProb} />
          </div>
        </div>
        {/* 우측 */}
        <div className="w-2/5 h-full flex flex-col justify-between">
          <CloverComp leafNum={leaf} />
          <TodayReviewComp TodayReviewData={TodayReviewData} />
        </div>
      </div>
    </div>
  );
};

export default HomeContent;
