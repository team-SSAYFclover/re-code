const Progressbar = ({
  height,
  roundWidth,
  percentage,
}: {
  height: string;
  roundWidth: string;
  percentage: number;
}) => {
  return (
    <div className={`w-full ${height} bg-[#F0F0F0] rounded-full`}>
      <div
        className={`h-full bg-gradient-to-r from-MAIN1 to-[#51A1FF] transition-all duration-500 ease-out rounded-full relative`}
        style={{ width: `${percentage}%` }}
      >
        <div className={`bg-[#217FEE] ${roundWidth} h-full rounded-full absolute right-0`} />
      </div>
    </div>
  );
};

export default Progressbar;
