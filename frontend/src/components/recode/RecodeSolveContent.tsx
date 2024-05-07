import { IGetRecodeRes } from '@/types/recode';
import { Resizable } from 're-resizable';
import MarkdownParser from './MarkdownParser';
import RecodeSolveBox from './RecodeSolveBox';

const RecodeSolveContent = ({ recode }: { recode: IGetRecodeRes }) => {
  return (
    <div className="w-full h-[calc(100vh-120px)] flex">
      <Resizable
        defaultSize={{ width: '50%', height: '100%' }}
        minWidth={'20%'}
        maxWidth={'80%'}
        enable={{
          top: false,
          right: true,
          bottom: false,
          left: false,
          topRight: false,
          bottomRight: false,
          bottomLeft: false,
          topLeft: false,
        }}
        handleStyles={{
          right: {
            width: '15px',
            height: '100%',
            right: '-15px',
            backgroundColor: '#F0F0F0',
            borderRight: '1px solid #d1d5db',
          },
        }}
      >
        <div className="w-full h-full p-4 overflow-y-scroll">
          <MarkdownParser markdown={recode.problem.content} />
        </div>
      </Resizable>
      <div className="flex-1 p-4 ml-[15px] overflow-y-scroll">
        <RecodeSolveBox recode={recode.recode} answer={recode.answers} />
      </div>
    </div>
  );
};

export default RecodeSolveContent;
