import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface IUserState {
  isLogin: boolean;
  name: string;
  avatarUrl: string;
  login: (name: string, avatarUrl: string) => void;
  logout: () => void;
}

const userStore = create(
  persist<IUserState>(
    (set) => ({
      isLogin: false,
      name: '',
      avatarUrl: '',
      login: (name, avatarUrl) =>
        set({
          isLogin: true,
          name: name,
          avatarUrl: avatarUrl,
        }),
      logout: () => {
        set({
          isLogin: false,
          name: '',
          avatarUrl: '',
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
