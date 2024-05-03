import Markdown from 'react-markdown';
import rehypeRaw from 'rehype-raw';
import rehypeSanitize from 'rehype-sanitize';

const RecodeSolveBox = ({ recode, answer }: { recode: string; answer: string[] }) => {
  console.log(answer);
  console.log(recode);

  const transformStringToInput = (str: string) => {
    // @TODO: 다른 예시들도 parsing 처리
    str = '    ' + str;
    str = str.replace(/\r\n/g, '\r\n    ');

    const strArr = str.split('‽▢');
    return strArr.join('<input type="text" placeholder="test"/>');
  };

  return (
    <Markdown rehypePlugins={[rehypeRaw, rehypeSanitize]}>
      {transformStringToInput(recode)}
    </Markdown>
  );
};

export default RecodeSolveBox;
