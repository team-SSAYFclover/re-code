interface IToggleProps {
  isOn: boolean;
  handleToggle: () => void;
}

const Toggle = ({ isOn, handleToggle }: IToggleProps) => {
  return (
    <div
      className={`w-[50px] h-[24px] flex items-center rounded-full p-1 cursor-pointer transition-all ${isOn ? 'bg-MAIN1' : 'bg-gray-200'}`}
      onClick={handleToggle}
    >
      <div
        className={`w-[22px] h-[20px] bg-white rounded-full shadow-md transition-transform ${isOn ? 'translate-x-[22px]' : 'translate-x-0'}`}
      ></div>
    </div>
  );
};

export default Toggle;
