export interface IGetUserInfoRes {
  name: string;
  avatarUrl: string;
  uuid: string;
  settings: {
    difficulty: 1; // 1 : 하, 2 : 중, 3 : 상
    notificationStatus: true;
    notificationTime: '15:00:00';
  };
}
