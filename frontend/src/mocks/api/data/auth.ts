import { IPostLoginRes } from '@/types/auth';
import { APIResponse } from '@/types/model';

export const postLoginRes: APIResponse<IPostLoginRes> = {
  status: 200,
  message: '로그인에 성공했습니다.',
  data: {
    name: '이수화',
    avatar_url: 'https://avatars.githubusercontent.com/u/72565083?v=4',
    settings: {
      difficulty: 1, // 1 : 하, 2 : 중, 3 : 상
      notificationStatus: true,
      notificationHour: 15,
      notificationMinute: 0,
    },
  },
};
