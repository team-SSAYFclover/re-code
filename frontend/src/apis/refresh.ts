import axios, { AxiosError } from 'axios';
import { axiosRefreshInstance } from './axiosInstance';

const refresh = async (error: AxiosError) => {
  if (error.response?.status === 401 && error.config) {
    try {
      const response = await axiosRefreshInstance.post('/users/reissue');
      const access_token = response.headers['access_token'];
      console.log('받은 응답', response);

      localStorage.setItem('RECODE_ACCESS_TOKEN', access_token);
      error.config.headers.Authorization = `Bearer ${access_token}`;

      return axios(error.config);
    } catch (refreshError) {
      console.error('리프레시 못함 :', refreshError);
      localStorage.setItem('RECODE_ACCESS_TOKEN', '');
      localStorage.setItem('RECODE_USER_STORE', '');
      window.location.href = '/';

      return Promise.reject(refreshError);
    }
  }

  return Promise.reject(error);
};

export default refresh;
