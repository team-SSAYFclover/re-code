import userStore from '@/stores/userStore';
import { GoHome } from 'react-icons/go';
import { LuLogOut } from 'react-icons/lu';
import { PiHandWaving, PiNotePencilLight, PiNotepad } from 'react-icons/pi';
import { useNavigate } from 'react-router-dom';

interface INavInfo {
  icon?: JSX.Element;
  label: string;
  url: string;
}

const Navbar = () => {
  const navigate = useNavigate();
  const currentPath = window.location.href.replace('//', '').split('/');
  const { logout } = userStore();

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
  ];

  const isRecode = currentPath.includes('recode');

  return (
    <nav
      className={` ${isRecode ? 'w-16  min-w-[64px]' : 'w-60 min-w-[200px]'} h-full shadow-lg relative`}
    >
      <div className="flex justify-center items-center h-20">{isRecode ? '' : 'recode'}</div>
      <div className="py-4">
        {navInfo.map((nav, idx) => {
          return (
            <div
              key={idx}
              className={`flex justify-center items-center h-12 m-2 text-BLACK font-bold cursor-pointer rounded-md hover:text-MAIN1 hover:bg-MAIN2 transition ease-in-out ${currentPath.includes(nav.url) && 'text-MAIN1 bg-MAIN2'}`}
              onClick={() => navigate(`/${nav.url}`)}
            >
              <div className={` ${isRecode ? 'text-[22px]' : 'text-lg'}`}>{nav.icon}</div>
              {!isRecode && <span className="text-sm ml-3">{nav.label}</span>}
            </div>
          );
        })}

        <button
          className="flex justify-center items-center absolute bottom-0 left-0 right-0 h-12 m-3 transition ease-in-out text-BLACK font-bold hover:bg-black/5 rounded-md outline-none"
          onClick={() => logout()}
        >
          <div className="text-lg">
            <LuLogOut />
          </div>
          {!isRecode && <span className="text-sm ml-3">로그아웃</span>}
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
