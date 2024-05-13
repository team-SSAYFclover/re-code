import { Outlet } from 'react-router-dom';
import Navbar from './Navbar';

const RecodeLayout = () => {
  const currentPath = window.location.href.replace('//', '').split('/');
  const isRecode = currentPath.includes('recode') && currentPath.length > 2;
  return (
    <div className="w-screen h-screen flex">
      <Navbar />
      <div className={`w-full h-full ${isRecode ? 'pl-16' : 'pl-60'}`}>
        <Outlet />
      </div>
    </div>
  );
};

export default RecodeLayout;
