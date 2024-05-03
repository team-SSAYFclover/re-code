import { AxiosError } from 'axios';

const refresh = async (error: AxiosError) => {
  // @TODO: refresh token 발급 처리
  if (error.response?.status === 401) {
    localStorage.setItem('RECODE_ACCESS_TOKEN', '');
    localStorage.setItem('RECODE_USER_STORE', '');
    window.location.href = '/';
  }

  return Promise.reject(error);
};

export default refresh;
