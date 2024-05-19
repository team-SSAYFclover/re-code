import ErrorLottie from '@/assets/lotties/error.json';
import Lottie from 'react-lottie';
import { useNavigate } from 'react-router-dom';

const ErrorPage = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: ErrorLottie,
  };

  const navigate = useNavigate();

  return (
    <div className="w-screen h-screen flex flex-col justify-center items-center">
      <Lottie options={defaultOptions} width={350} height={350} />
      <div className="text-xl">앗! 잘못된 경로로 접근했어요</div>
      <button
        className="bg-MAIN1 text-white rounded-full px-6 py-2 my-4"
        onClick={() => navigate('/')}
      >
        메인페이지로 돌아가기
      </button>
    </div>
  );
};

export default ErrorPage;
