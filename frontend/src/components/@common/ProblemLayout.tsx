import Header from '@/components/@common/Header';
import Navbar from '@/components/@common/Navbar';
import { Outlet } from 'react-router-dom';

const ProblemLayout = () => {
  return (
    <div className="w-screen h-screen flex  min-w-[1200px]">
      <Navbar />
      <div className="w-full h-full pl-60">
        <Header />
        <main className="h-[calc(100vh-64px)] w-full">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default ProblemLayout;
