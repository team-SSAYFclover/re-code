import Header from '@/components/@common/Header';
import Navbar from '@/components/@common/Navbar';
import { Outlet } from 'react-router-dom';

const Layout = () => {
  const currentPath = window.location.href.replace('//', '').split('/');
  const isRecode = currentPath.includes('recode') && currentPath.length > 2;
  return (
    <div className=" h-screen flex">
      <Navbar />
      <div className={`w-full h-full ${isRecode ? 'pl-16' : 'pl-60'}`}>
        <Header />
        <main className="h-[calc(100vh-64px)]">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default Layout;
