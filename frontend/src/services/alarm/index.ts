import { axiosCommonInstance } from '@/apis/axiosInstance';
import { IGetAlarmRes } from '@/types/alarm';
import { APIResponse } from '@/types/model';

export const getTodayAlarm = async (): Promise<APIResponse<IGetAlarmRes>> => {
  const res = await axiosCommonInstance.get('/api/notifications');
  return res.data;
};
