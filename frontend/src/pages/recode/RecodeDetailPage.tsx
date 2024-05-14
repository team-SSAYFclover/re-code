import ErrorContent from '@/components/@common/ErrorContent';
import LoadingContent from '@/components/@common/LoadingContent';
import RecodeHeader from '@/components/recode/RecodeHeader';
import RecodeSolveContent from '@/components/recode/RecodeSolveContent';
import { useRecode } from '@/hooks/recode/useRecode';
import { useParams } from 'react-router-dom';

const RecodeDetailPage = () => {
  const params = useParams();
  const { useGetTodayRecodeList } = useRecode();
  const { data: todayRecodeList } = useGetTodayRecodeList();

  const { useGetRecode } = useRecode();
  const { data: recode, isLoading, isError } = useGetRecode(params.codeId || '');

  if (isLoading) {
    return <LoadingContent />;
  }

  if (!recode || isError || !todayRecodeList) {
    return <ErrorContent />;
  }

  return (
    <div className="w-full h-full pl-16">
      <RecodeHeader recode={recode} todayRecodeList={todayRecodeList} />
      <RecodeSolveContent recode={recode} todayRecodeList={todayRecodeList} />
    </div>
  );
};

export default RecodeDetailPage;
