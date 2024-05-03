import { APIResponse } from '@/types/model';
import { IGetUserInfoRes } from '@/types/user';

export const postLoginRes: APIResponse<null> = {
  status: 200,
  message: '로그인에 성공했습니다.',
  data: null,
};

export const getUserRes: APIResponse<IGetUserInfoRes> = {
  status: 200,
  message: '유저 정보 조회에 성공했습니다',
  data: {
    name: '이수화',
    avatarUrl: 'https://avatars.githubusercontent.com/u/72565083?v=4',
    uuid: '9bb3dfef-edcf-4a77-ad48-d7356cc6d1dd',
    settings: {
      difficulty: 1, // 1 : 하, 2 : 중, 3 : 상
      notificationStatus: true,
      notificationTime: '15:00:00',
    },
  },
};

// export const getUserCodeRes: APIResponse<IGetUserCodeRes> = {
//   status: 200,
//   message:
// }
