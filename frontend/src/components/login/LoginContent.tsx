import logo from '@/assets/logo2.png';
import MainLottie from '@/assets/lotties/main.json';
import GithubLoginBtn from '@/components/login/GithubLoginBtn';
import { motion } from 'framer-motion';
import { VscDebugStart } from 'react-icons/vsc';
import Lottie from 'react-lottie';
import { useNavigate } from 'react-router-dom';

const LoginContent = () => {
  const navigate = useNavigate();
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: MainLottie,
  };

  const varient = {
    hidden: { opacity: 0, y: 30 },
    visible: { opacity: 1, y: 0, transition: { duration: 1 } },
  };

  return (
    <motion.div
      variants={varient}
      initial="hidden"
      whileInView="visible"
      className="w-[80%] h-[calc(100%-64px)] mx-auto flex justify-center items-center"
    >
      <div className="w-1/2">
        <img alt="logo" src={logo} className="w-44 py-4" />
        <div className="leading-7 text-[18px]">
          <span className="text-MAIN1 font-semibold">Re:code</span>는 알고리즘 역량을 향상시키기
          위한 <span className="text-MAIN1 font-semibold">효율적인 알고리즘 복습 서비스</span>
          입니다. 지금 Re:code에 가입하여 알고리즘 마스터가 되기 위한 첫 걸음을 시작하세요!
        </div>
        <GithubLoginBtn />
        <hr />
        <div className="leading-7 py-6 text-[18px]">
          re:code를 시작하기 어려우시다면 가이드를 통해 서비스를 쉽게 이용해볼까요?
        </div>
        <div className="flex justify-end">
          <button
            className="w-56 h-12 bg-MAIN1 text-white rounded-full shadow-md flex justify-center items-center hover:bg-[#2CD8AE] "
            onClick={() => navigate('/guide')}
          >
            <VscDebugStart size={24} />
            <span className="pl-2">시작 가이드로 이동하기</span>
          </button>
        </div>
      </div>
      <Lottie options={defaultOptions} width={350} height={350} />
    </motion.div>
  );
};

export default LoginContent;
