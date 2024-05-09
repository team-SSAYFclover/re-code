import { axiosCommonInstance } from '@/apis/axiosInstance';
import { APIBasicResponse, APIResponse } from '@/types/model';
import { IGetUserInfoRes } from '@/types/user';

export const getUserInfo = async (): Promise<APIResponse<IGetUserInfoRes>> => {
  const res = await axiosCommonInstance.get('/users');
  return res.data;
};

export const modifyUserInfo = async (
  key: string,
  value: string | boolean | number
): Promise<APIBasicResponse> => {
  const res = await axiosCommonInstance.patch('/users/setting', {
    [key]: value,
  });

  return res.data;
};
