import HomeContent from '@/components/home/HomeContent';
import LoginContent from '@/components/login/LoginContent';
import userStore from '@/stores/userStore';

const HomePage = () => {
  const { isLogin } = userStore();

  return (
    <div
      className={`h-full px-20 ${!isLogin && 'bg-gradient-to-b from-[#ffffff] from-0% to-[#e3fff8] to-100%'}`}
    >
      {isLogin ? <HomeContent /> : <LoginContent />}
    </div>
  );
};

export default HomePage;
