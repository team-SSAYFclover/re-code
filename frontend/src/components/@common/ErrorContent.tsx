import ErrorLottie from '@/assets/lotties/error.json';
import Lottie from 'react-lottie';

const ErrorContent = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: ErrorLottie,
  };

  return (
    <div className="w-full h-full flex justify-center items-center flex-col">
      <Lottie options={defaultOptions} width={250} height={250} />
      <span className="text-lg text-gray-500 text-center">
        <span className="font-semibold text-BLACK pb-2">일시적인 오류가 발생했습니다.</span>
        <br />
        서비스 이용에 불편을 드려 죄송합니다.
        <br />
        잠시 후 다시 이용해주세요.
      </span>
    </div>
  );
};

export default ErrorContent;
