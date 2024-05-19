import logo from '../assets/Logo.png';

const Header = () => {
  return (
    <>
      <div className="flex w-full bg-MAIN2 h-[60px] justify-center items-center px-[30%]">
        <img src={logo} alt="" className="w-full" />
      </div>
    </>
  );
};

export default Header;
