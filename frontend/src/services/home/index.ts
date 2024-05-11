import { axiosCommonInstance } from '@/apis/axiosInstance';
import { APIResponse, IMainInfo } from '@/types/model';

export const getMainInfo = async (): Promise<APIResponse<IMainInfo>> => {
  try {
    const res = await axiosCommonInstance.get('/statistics');
    console.log('service gotten : ', res);
    return res.data;
  } catch (error) {
    console.error('서비스 에러!', error);
    throw error;
  }
};
