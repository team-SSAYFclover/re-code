import { IGetProblemRes } from '@/pages/recode/RecodeDetailPage';
import { Resizable } from 're-resizable';
import MarkdownParser from './MarkdownParser';
import RecodeSolveBox from './RecodeSolveBox';

const RecodeSolveContent = ({ recode }: { recode: IGetProblemRes }) => {
  return (
    <div className="w-full h-[calc(100vh-120px)] flex">
      <div className="flex-1 p-4 overflow-y-scroll prose">
        <MarkdownParser markdown={recode.content} />
      </div>
      <Resizable
        defaultSize={{ width: '50%', height: '100%' }}
        minWidth={'20%'}
        maxWidth={'80%'}
        enable={{
          top: false,
          right: false,
          bottom: false,
          left: true,
          topRight: false,
          bottomRight: false,
          bottomLeft: false,
          topLeft: false,
        }}
        handleStyles={{
          left: {
            width: '15px',
            height: '100%',
            left: '0px',
            backgroundColor: '#F0F0F0',
            borderRight: '1px solid #d1d5db',
          },
        }}
      >
        <div className="h-full ml-[15px] p-2 relative overflow-y-scroll">
          <RecodeSolveBox recode={recode.recode} answer={recode.answers} />
        </div>
      </Resizable>
    </div>
  );
};

export default RecodeSolveContent;
