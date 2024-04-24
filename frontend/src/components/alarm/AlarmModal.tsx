import AlarmItem from './AlarmItem';

const AlarmModal = ({ onClose }: { onClose: () => void }) => {
  return (
    <>
      <div className="w-screen h-screen fixed top-0 left-0" onClick={() => onClose()}></div>
      <div className="absolute top-16 right-14 w-88 bg-white rounded-md shadow-lg p-4 z-10">
        <span className="text-lg">알림</span>
        <AlarmItem />
      </div>
    </>
  );
};

export default AlarmModal;
