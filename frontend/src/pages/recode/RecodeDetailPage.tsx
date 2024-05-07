import RecodeBottom from '@/components/recode/RecodeBottom';
import RecodeHeader from '@/components/recode/RecodeHeader';
import RecodeSolveContent from '@/components/recode/RecodeSolveContent';
import { useRecode } from '@/hooks/recode/useRecode';
import { useParams } from 'react-router-dom';

const RecodeDetailPage = () => {
  const params = useParams();

  const { useGetRecode } = useRecode();
  const { data, isLoading } = useGetRecode(params.codeId || '');

  if (isLoading) {
    return <div>로딩중</div>;
  }

  if (!data) {
    return <div>데이터 없음</div>;
  }

  return (
    <div className="w-full h-full">
      <RecodeHeader recode={data} />
      <RecodeSolveContent recode={data} />
      <RecodeBottom recode={data} />
    </div>
  );
};

export default RecodeDetailPage;
