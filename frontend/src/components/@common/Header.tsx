import defaultProfile from '@/assets/default_profile.png';
import MySettingModal from '@/components/my/MySettingModal';
import userStore from '@/stores/userStore';
import { useState } from 'react';
import AlarmModal from '../alarm/AlarmModal';

const Header = () => {
  const { isLogin, avatarUrl, name } = userStore();
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
      <div className="w-full h-16 flex justify-end items-center pr-10">
        {isLogin && (
          <button
            onClick={() => handleOpen('setting')}
            className="flex justify-center items-center"
          >
            {/* <span className="cursor-pointer z-10" onClick={() => handleOpen('alarm')}>
              <FiBell size="25" color="#484848" />
            </span> */}
            <img
              src={avatarUrl === '' ? defaultProfile : avatarUrl}
              alt="profile"
              className="w-8 z-10 rounded-full"
            />
            <span className="text-gray-200 px-2">|</span>
            <div className="text-sm text-BLACK">@ {name}</div>
          </button>
        )}
      </div>
      {isShowSetting && <MySettingModal onClose={() => setIsShowSetting(false)} />}
      {isShowAlarm && <AlarmModal onClose={() => setIsShowAlarm(false)} />}
    </>
  );
};

export default Header;
