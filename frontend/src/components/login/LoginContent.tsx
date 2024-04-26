import MainLottie from '@/assets/lotties/main.json';
import GithubLoginBtn from '@/components/login/GithubLoginBtn';
import Lottie from 'react-lottie';

const LoginContent = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: MainLottie,
  };

  return (
    <div className="w-[80%] h-[90%] mx-auto flex justify-center items-center">
      <div className="w-1/2">
        <div className="leading-6">
          <div className="text-4xl font-semibold py-2">RE:CODE</div>
          <span className="text-MAIN1 font-semibold">Re:code</span>는 알고리즘 역량을 향상시키기
          위한 <span className="text-MAIN1 font-semibold">효율적인 알고리즘 복습 서비스</span>
          입니다. <br />
          지금 Re:code에 가입하여 알고리즘 마스터가 되기 위한 첫 걸음을 시작하세요!
        </div>
        <br />
        <GithubLoginBtn />
      </div>
      <Lottie options={defaultOptions} width={350} height={350} />
    </div>
  );
};

export default LoginContent;
