import logo from '@/assets/logo.png';
import userStore from '@/stores/userStore';
import { GoHome } from 'react-icons/go';
import { LuLogOut } from 'react-icons/lu';
import { PiArrowSquareOutLight, PiHandWaving, PiNotePencilLight, PiNotepad } from 'react-icons/pi';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from '@/services/user';

interface INavInfo {
  icon?: JSX.Element;
  label: string;
  url: string;
}

const Navbar = () => {
  const navigate = useNavigate();
  const currentPath = window.location.href.replace('//', '').split('/');
  const { logout, isLogin } = userStore();

  const handleLogout = () => {
    logoutUser();
    logout();
  };

  const navInfo: INavInfo[] = [
    {
      icon: <GoHome />,
      label: '대시보드',
      url: '',
    },
    {
      icon: <PiNotePencilLight />,
      label: '문제 풀기',
      url: 'recode',
    },
    {
      icon: <PiNotepad />,
      label: '문제 조회',
      url: 'problem',
    },
    {
      icon: <PiHandWaving />,
      label: '시작 가이드',
      url: 'guide',
    },
    {
      icon: <PiArrowSquareOutLight />,
      label: '익스텐션',
      url: 'https://chromewebstore.google.com/detail/recode-uploader/ppbaladjjpmepkmaafeidledkhmjdill',
    },
  ];

  const isRecode = currentPath.includes('recode') && currentPath.length > 2;

  return (
    <nav
      className={` ${isRecode ? 'w-16 min-w-[64px]' : 'w-60 min-w-[200px]'} h-screen shadow-lg fixed top-0 left-0 bottom-0 z-10 bg-white`}
    >
      <div className="flex justify-center items-center h-auto">
        {isRecode ? (
          <div className="w-full h-20">&nbsp;</div>
        ) : (
          <img
            alt="logo"
            src={logo}
            className="w-28 pt-4 cursor-pointer"
            onClick={() => navigate('/')}
          />
        )}
      </div>
      <div className="py-4">
        {navInfo.map((nav, idx) => {
          return (
            <div
              key={idx}
              className={`flex justify-center items-center h-12 m-2 text-BLACK font-bold cursor-pointer rounded-md hover:text-MAIN1 hover:bg-MAIN2 transition ease-in-out ${currentPath.includes(nav.url) && 'text-MAIN1 bg-MAIN2'}`}
              onClick={() => {
                if (nav.label === '익스텐션') {
                  window.open(nav.url, '_blank');
                } else {
                  navigate(`/${nav.url}`);
                }
              }}
            >
              <div className={` ${isRecode ? 'text-[22px]' : 'text-lg'}`}>{nav.icon}</div>
              {!isRecode && <span className="text-sm ml-3">{nav.label}</span>}
            </div>
          );
        })}

        {!isRecode && isLogin && (
          <button
            className="flex justify-center items-center absolute bottom-0 left-0 right-0 h-12 m-3 transition ease-in-out text-BLACK font-bold hover:bg-black/5 rounded-md outline-none"
            onClick={handleLogout}
          >
            <div className="text-lg">
              <LuLogOut />
            </div>
            <span className="text-sm ml-3">로그아웃</span>
          </button>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
