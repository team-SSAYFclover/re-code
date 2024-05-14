import clover from '@/assets/clover.png';
import Modal from '../../@common/Modal';

const RecodeCodeModal = ({ code, onClose }: { code: string; onClose: () => void }) => {
  return (
    <Modal width="w-[70%]" height="h-[80%]" onClose={onClose}>
      <div className="w-full h-full mx-auto">
        <div className="flex">
          <div className="w-[56px] px-2">
            <img src={clover} alt="clover" />
          </div>
          <div className="flex-1">
            <div className="text-xl font-semibold">기존 코드</div>
            <div className="text-sm">기존에 작성된 코드를 확인하여 정답률을 높여보아요.</div>
          </div>
        </div>

        <div className="h-[calc(100%-108px)] overflow-y-scroll p-3">
          <pre>{code}</pre>
        </div>
      </div>
    </Modal>
  );
};

export default RecodeCodeModal;
