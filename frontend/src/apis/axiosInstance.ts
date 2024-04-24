import axios from 'axios';

const axiosRequestConfig = {
  baseURL: import.meta.env.VITE_NODE_ENV === 'development' ? '' : import.meta.env.VITE_BASE_URL,
};

export const axiosCommonInstance = axios.create(axiosRequestConfig);
