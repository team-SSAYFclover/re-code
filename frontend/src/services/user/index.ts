import { axiosCommonInstance } from '@/apis/axiosInstance';
import { APIResponse } from '@/types/model';
import { IGetUserInfoRes } from '@/types/user';

export const getUserInfo = async (): Promise<APIResponse<IGetUserInfoRes>> => {
  const res = await axiosCommonInstance.get('/users');
  return res.data;
};
