import { IGetAlarmRes } from '@/types/alarm';
import { APIResponse } from '@/types/model';

export const getAlarmRes: APIResponse<IGetAlarmRes> = {
  status: 200,
  message: '알림 정보 조회에 성공했습니다.',
  data: {
    recodecnt: 5, // 복습문제 개수
    date: '2024-04-24T13:30:00Z', // 날짜 YYYY-MM-DDTHH:mm:ss.sssZ
  },
};
