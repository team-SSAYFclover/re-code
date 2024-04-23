import { difficultyLevelType } from '@/types/model';
import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface IUserState {
  isLogin: boolean;
  name: string;
  difficulty: difficultyLevelType;
  notifStatus: boolean;
  notifHour: number;
  notifMinute: number;
  avatar_url: string;
  login: (
    name: string,
    avatar_url: string,
    difficulty: difficultyLevelType,
    notifStatus: boolean,
    notifHour: number,
    notifMinute: number
  ) => void;
  logout: () => void;
  // @TODO: 유저 설정 변경 시 전역에도 수정하도록 메서드 추가
  // updateSetting: (
  //   difficulty: difficultyLevelType,
  //   notifStatus: boolean,
  //   notifHour: number,
  //   notifMinute: number
  // ) => void;
}

const userStore = create(
  persist<IUserState>(
    (set) => ({
      isLogin: true,
      name: '이수화',
      difficulty: 1,
      notifStatus: true,
      notifHour: 10,
      notifMinute: 0,
      avatar_url: 'https://avatars.githubusercontent.com/u/72565083?v=4',
      login: (name, avatar_url, difficulty, notifStatus, notifHour, notifMinute) =>
        set({
          isLogin: true,
          name: name,
          avatar_url: avatar_url,
          difficulty: difficulty,
          notifStatus: notifStatus,
          notifHour: notifHour,
          notifMinute: notifMinute,
        }),
      logout: () => {
        set({
          isLogin: false,
          name: '',
          avatar_url: '',
          difficulty: 1,
          notifStatus: false,
          notifHour: 0,
          notifMinute: 0,
        });
        localStorage.setItem('RECODE_ACCESS_TOKEN', '');
      },
    }),
    {
      name: 'RECODE_USER_STORE',
    }
  )
);

export default userStore;
