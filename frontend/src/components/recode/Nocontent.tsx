const Nocontent = ({ text, icon }: { text: JSX.Element | string; icon?: JSX.Element }) => {
  return (
    <div className="w-full h-full text-center flex flex-col justify-center items-center">
      <div className="w-full">{icon}</div>
      <div>{text}</div>
    </div>
  );
};

export default Nocontent;
