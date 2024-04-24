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
  const currentPath = window.location.href.split('/').at(-1);

  const navInfo: INavInfo[] = [
    {
      icon: <GoHome />,
      label: '대시보드',
      url: '',
    },
    {
      icon: <PiNotepad />,
      label: '문제 조회',
      url: 'problem',
    },
    {
      icon: <PiNotePencilLight />,
      label: '문제 풀기',
      url: 'recode',
    },
    {
      icon: <PiHandWaving />,
      label: '시작 가이드',
      url: 'guide',
    },
  ];

  if (currentPath === 'recode') {
    return (
      <nav className="w-16 h-full shadow-lg relative">
        <div className="flex justify-center items-center h-20"></div>
        <div className="py-4">
          {navInfo.map((nav, idx) => {
            return (
              <div
                key={idx}
                className={`flex justify-center items-center h-12 m-2 text-BLACK font-bold cursor-pointer rounded-md hover:text-MAIN1 hover:bg-MAIN2 transition ease-in-out ${currentPath === nav.url && 'text-MAIN1 bg-MAIN2'}`}
                onClick={() => navigate(`/${nav.url}`)}
              >
                <div className="text-[22px]">{nav.icon}</div>
              </div>
            );
          })}

          <button className="flex justify-center items-center absolute bottom-0 left-0 right-0 h-12 m-3 text-BLACK font-bold">
            <div className="text-lg">
              <LuLogOut />
            </div>
          </button>
        </div>
      </nav>
    );
  }

  return (
    <nav className="w-60 h-full shadow-lg relative">
      <div className="flex justify-center items-center h-20">re:code</div>
      <div className="py-4">
        {navInfo.map((nav, idx) => {
          return (
            <div
              key={idx}
              className={`flex justify-center items-center h-12 m-2 text-BLACK font-bold cursor-pointer rounded-md hover:text-MAIN1 hover:bg-MAIN2 transition ease-in-out ${currentPath === nav.url && 'text-MAIN1 bg-MAIN2'}`}
              onClick={() => navigate(`/${nav.url}`)}
            >
              <div className="text-lg">{nav.icon}</div>
              <span className="text-sm ml-3">{nav.label}</span>
            </div>
          );
        })}

        <button className="flex justify-center items-center absolute bottom-0 left-0 right-0 h-12 m-3 transition ease-in-out text-BLACK font-bold hover:bg-black/5 rounded-md outline-none">
          <div className="text-lg">
            <LuLogOut />
          </div>
          <span className="text-sm ml-3">로그아웃</span>
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
