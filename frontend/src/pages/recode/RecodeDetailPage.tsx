import RecodeHeader from '@/components/recode/RecodeHeader';
import RecodeSolveContent from '@/components/recode/RecodeSolveContent';
import { useRecode } from '@/hooks/recode/useRecode';
import recodeListStore from '@/stores/recodeListStore';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';

const RecodeDetailPage = () => {
  const params = useParams();
  const { useGetTodayRecodeList } = useRecode();
  const { data: todayRecodeList } = useGetTodayRecodeList();
  const { setTodayRecodes } = recodeListStore();

  useEffect(() => {
    if (!todayRecodeList) return;
    setTodayRecodes(todayRecodeList);
  }, [todayRecodeList, setTodayRecodes]);

  const { useGetRecode } = useRecode();
  const { data: recode, isLoading } = useGetRecode(params.codeId || '');

  if (isLoading) {
    return <div>로딩중</div>;
  }

  if (!recode) {
    return <div>데이터 없음</div>;
  }

  return (
    <div className="w-full h-full">
      <RecodeHeader recode={recode} />
      <RecodeSolveContent recode={recode} />
    </div>
  );
};

export default RecodeDetailPage;
