import { axiosCommonInstance } from '@/apis/axiosInstance';
import { APIResponse } from '@/types/model';
import { IGetRecodeRes, IGetTodayRecodeListRes } from '@/types/recode';

export const getTodayRecodeList = async (): Promise<APIResponse<IGetTodayRecodeListRes>> => {
  const res = await axiosCommonInstance.get('/statistics/today/reviews');
  return res.data;
};

export const getRecode = async (codeId: string): Promise<APIResponse<IGetRecodeRes>> => {
  const res = await axiosCommonInstance.get(`/problems/recode/${codeId}`);
  return res.data;
};

export const completeRecode = async (codeId: string): Promise<APIResponse<null>> => {
  const res = await axiosCommonInstance.put(`/problems/recode/${codeId}`);
  return res.data;
};
