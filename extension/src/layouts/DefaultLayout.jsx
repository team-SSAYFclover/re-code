import { Outlet } from "react-router-dom";
import Header from "../components/Header";

const DefaultLayout = () => {
  return (
    <div className="flex flex-col w-full h-full">
      <Header />
      <Outlet />
    </div>
  );
};

export default DefaultLayout;
