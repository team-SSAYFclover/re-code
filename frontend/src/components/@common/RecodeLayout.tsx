import { Outlet } from 'react-router-dom';
import Navbar from './Navbar';

const RecodeLayout = () => {
  return (
    <div className="w-screen h-screen flex  min-w-[1200px]">
      <Navbar />
      <div className="w-full h-full">
        <Outlet />
      </div>
    </div>
  );
};

export default RecodeLayout;
