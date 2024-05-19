import LoadingLottie from '@/assets/lotties/loading.json';
import Toast from '@/components/@common/Toast';
import { useUser } from '@/hooks/user/useUser';
import userStore from '@/stores/userStore';
import { useEffect, useState } from 'react';
import Lottie from 'react-lottie';
import { useNavigate, useSearchParams } from 'react-router-dom';

const RedirectPage = () => {
  const defaultOptions = {
    loop: true,
    autoplay: true,
    animationData: LoadingLottie,
  };

  const naviagate = useNavigate();
  const { login } = userStore();
  const [searchParams] = useSearchParams();
  const accessToken = searchParams.get('access_token');
  const [isTokenStored, setIsTokenStored] = useState<boolean>(false);
  const { useGetUser } = useUser();
  const { data, isLoading } = useGetUser(isTokenStored);

  useEffect(() => {
    if (accessToken) {
      localStorage.setItem('RECODE_ACCESS_TOKEN', accessToken);
      setIsTokenStored(true);
    } else {
      Toast.error('로그인을 다시 진행해 주세요.');
      naviagate('/');
    }
  }, [accessToken, naviagate]);

  useEffect(() => {
    if (data && !isLoading) {
      login(data.name, data.avatarUrl);
      naviagate('/');
    }
  }, [data, isLoading, login, naviagate]);

  return (
    <div className="w-screen h-screen flex justify-center items-center flex-col">
      <Lottie options={defaultOptions} width={300} height={300} />
      <span className="text-xl text-MAIN1">로그인 진행중 ...</span>
    </div>
  );
};

export default RedirectPage;
