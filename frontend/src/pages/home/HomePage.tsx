import HomeContent from '@/components/home/HomeContent';
import LoginContent from '@/components/login/LoginContent';
import userStore from '@/stores/userStore';

const HomePage = () => {
  const { isLogin } = userStore();

  return <div className="h-[calc(81vh)]">{isLogin ? <HomeContent /> : <LoginContent />}</div>;
};

export default HomePage;
