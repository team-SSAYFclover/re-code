import defaultProfile from '@/assets/default_profile.png';
import { FiBell } from 'react-icons/fi';

const Header = () => {
  // const { isLogin } = userStore();
  const isLogin = true;

  return (
    <div className="w-full h-16 flex justify-end items-center">
      {isLogin && (
        <>
          <span className="cursor-pointer">
            <FiBell size="25" color="#484848" />
          </span>
          <img src={defaultProfile} alt="profile" className="w-8 mx-4 cursor-pointer" />
        </>
      )}
    </div>
  );
};

export default Header;
