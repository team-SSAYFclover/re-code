import Header from '@/components/@common/Header';
import Navbar from '@/components/@common/Navbar';
import { Outlet } from 'react-router-dom';

const ProblemLayout = () => {
  return (
    <div className="w-screen h-screen flex">
      <Navbar />
      <div className="w-full h-full">
        <Header />
        <main className="h-[calc(100vh-64px)] w-full ps-20">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default ProblemLayout;
