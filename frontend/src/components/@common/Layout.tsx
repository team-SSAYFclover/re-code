import { Outlet } from 'react-router-dom';
import Header from './Header';
import Navbar from './Navbar';

const Layout = () => {
  return (
    <div className="w-screen h-screen flex">
      <Navbar />
      <div className="w-full h-full">
        <Header />
        <main className="h-[calc(100vh-64px)]">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default Layout;
