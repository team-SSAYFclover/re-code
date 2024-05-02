import Markdown from 'react-markdown';
import rehypeRaw from 'rehype-raw';
import rehypeSanitize from 'rehype-sanitize';

const MarkdownParser = ({ markdown }: { markdown: string }) => {
  return <Markdown rehypePlugins={[rehypeRaw, rehypeSanitize]}>{markdown}</Markdown>;
};

export default MarkdownParser;
