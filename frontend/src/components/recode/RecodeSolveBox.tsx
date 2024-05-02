import Markdown from 'react-markdown';
import rehypeHighlight from 'rehype-highlight';
import rehypeRaw from 'rehype-raw';
import rehypeSanitize from 'rehype-sanitize';

const RecodeSolveBox = ({ recode, answer }: { recode: string; answer: string[] }) => {
  console.log(answer);
  return <Markdown rehypePlugins={[rehypeRaw, rehypeSanitize, rehypeHighlight]}>{recode}</Markdown>;
};

export default RecodeSolveBox;
