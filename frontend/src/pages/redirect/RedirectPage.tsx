import LoadingLottie from '@/assets/lotties/loading.json';
import Toast from '@/components/@common/Toast';
import { useUser } from '@/hooks/user/useUser';
import userStore from '@/stores/userStore';
import Lottie from 'react-lottie';
import { useNavigate, useSearchParams } from 'react-router-dom';

const RedirectPage = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: LoadingLottie,
  };

  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const accessToken = searchParams.get('access_token');

  if (!accessToken) {
    Toast.error('로그인을 다시 진행해 주세요.');
    navigate('/');
    return;
  }

  localStorage.setItem('RECODE_ACCESS_TOKEN', accessToken);

  const { login } = userStore();
  const { useGetUser } = useUser();
  const { data, isLoading } = useGetUser();

  if (!data || isLoading) {
    console.log('data', data, isLoading);
    return;
  }

  if (accessToken) {
    login(data.name, data.avatarUrl);
    navigate('/');
  }

  return (
    <div className="w-screen h-screen flex justify-center items-center flex-col">
      <Lottie options={defaultOptions} width={300} height={300} />
      <span className="text-xl text-MAIN1">로그인 진행중 ...</span>
    </div>
  );
};

export default RedirectPage;
