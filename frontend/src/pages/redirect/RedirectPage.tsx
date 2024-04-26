import LoadingLottie from '@/assets/lotties/loading.json';
import Lottie from 'react-lottie';

const RedirectPage = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: LoadingLottie,
  };

  // @TODO: 쿠키에 accessToken 있으면 회원 정보 api 호출해서 가져오기

  return (
    <div className="w-screen h-screen flex justify-center items-center flex-col">
      <Lottie options={defaultOptions} width={300} height={300} />
      <span className="text-xl text-MAIN1">로그인 진행중 ...</span>
    </div>
  );
};

export default RedirectPage;
