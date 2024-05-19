import LoadingLottie from '@/assets/lotties/loading.json';
import Lottie from 'react-lottie';

const LoadingContent = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: LoadingLottie,
  };

  return (
    <div className="w-full h-full flex justify-center items-center flex-col">
      <Lottie options={defaultOptions} width={250} height={250} />
      <span className="text-lg text-gray-500 text-center">
        <span className="font-semibold text-BLACK pb-2">데이터를 받아오는 중입니다.</span>
        <br />
        잠시만 기다려주세요.
      </span>
    </div>
  );
};

export default LoadingContent;
