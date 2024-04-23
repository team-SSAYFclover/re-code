import defaultProfile from '@/assets/default_profile.png';
import MySettingModal from '@/components/my/MySettingModal';
import userStore from '@/stores/userStore';
import { useState } from 'react';
import { FiBell } from 'react-icons/fi';

const Header = () => {
  const { isLogin, avatar_url } = userStore();

  const [isShowSetting, setIsShowSetting] = useState<boolean>(false);

  return (
    <>
      <div className="w-full h-16 flex justify-end items-center">
        {isLogin && (
          <>
            <span className="cursor-pointer">
              <FiBell size="25" color="#484848" />
            </span>
            <img
              src={avatar_url === '' ? defaultProfile : avatar_url}
              alt="profile"
              className="w-8 mx-4 cursor-pointer"
              onClick={() => setIsShowSetting(!isShowSetting)}
            />
          </>
        )}
      </div>
      {isShowSetting && <MySettingModal onClose={() => setIsShowSetting(false)} />}
    </>
  );
};

export default Header;
