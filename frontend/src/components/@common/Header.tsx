import defaultProfile from '@/assets/default_profile.png';
import MySettingModal from '@/components/my/MySettingModal';
import userStore from '@/stores/userStore';
import { useState } from 'react';
import { FiBell } from 'react-icons/fi';
import AlarmModal from '../alarm/AlarmModal';

const Header = () => {
  const { isLogin, avatar_url } = userStore();

  const [isShowSetting, setIsShowSetting] = useState<boolean>(false);
  const [isShowAlarm, setIsShowAlarm] = useState<boolean>(false);

  const handleOpen = (type: 'setting' | 'alarm') => {
    if (type === 'setting') {
      if (isShowAlarm) {
        setIsShowAlarm(false);
      }

      setIsShowSetting(!isShowSetting);
    } else {
      if (isShowSetting) {
        setIsShowSetting(false);
      }

      setIsShowAlarm(!isShowAlarm);
    }
  };

  return (
    <>
      <div className="w-full h-16 flex justify-end items-center">
        {isLogin && (
          <>
            <span className="cursor-pointer z-10" onClick={() => handleOpen('alarm')}>
              <FiBell size="25" color="#484848" />
            </span>
            <img
              src={avatar_url === '' ? defaultProfile : avatar_url}
              alt="profile"
              className="w-8 mx-4 cursor-pointer z-10"
              onClick={() => handleOpen('setting')}
            />
          </>
        )}
      </div>
      {isShowSetting && <MySettingModal onClose={() => setIsShowSetting(false)} />}
      {isShowAlarm && <AlarmModal onClose={() => setIsShowAlarm(false)} />}
    </>
  );
};

export default Header;
