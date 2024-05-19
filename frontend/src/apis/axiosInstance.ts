import axios from 'axios';
import refresh from './refresh';
import setAuthorization from './setAuthorization';

const BASE_URL =
  import.meta.env.VITE_NODE_ENV === 'development' ? '' : import.meta.env.VITE_BASE_URL;

const basicRequestConfig = {
  baseURL: BASE_URL,
};

const refreshTokenReqestConfig = {
  baseURL: BASE_URL,
  withCredentials: true,
};

export const axiosCommonInstance = axios.create(basicRequestConfig);
export const axiosRefreshInstance = axios.create(refreshTokenReqestConfig);

axiosCommonInstance.interceptors.request.use(setAuthorization);
axiosRefreshInstance.interceptors.request.use(setAuthorization);

axiosCommonInstance.interceptors.response.use(null, refresh);
axiosRefreshInstance.interceptors.request.use(null, refresh);
