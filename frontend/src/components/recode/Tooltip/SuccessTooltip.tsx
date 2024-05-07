const SuccessTooltip = ({ input, answer }: { input: string; answer: string }) => {
  if (input.length < answer.length) {
    return (
      <div className="p-2 bg-white">
        <div className="text-[#006AFF] py-1">
          정답 {answer.length}자 중 {input.length}자를 입력하셨네요. 정답에 가까워지고 있어요!
        </div>
        <div className="h-10 text-lg bg-[#D6E7FF] flex justify-center items-center">
          {input.split('').map((x, idx) => {
            return (
              <span
                key={idx}
                className={`${answer[idx] === x ? 'text-[#006AFF]' : 'text-[#6A6A6A]'}`}
              >
                {x}
              </span>
            );
          })}
          <span>{Array.from({ length: answer.length - input.length }, () => ' _ ').join(' ')}</span>
        </div>
      </div>
    );
  }

  return (
    <div className="p-2 bg-white">
      <div className="text-[#006AFF] py-1">정답입니다!</div>
      <div className="px-4 h-10 text-lg bg-[#D6E7FF] flex justify-center items-center">
        {input.split('').map((x, idx) => {
          return (
            <span
              key={idx}
              className={`px-1 ${answer[idx] === x ? 'text-[#6A6A6A]' : 'text-[#006AFF]'}`}
            >
              {x}
            </span>
          );
        })}
      </div>
    </div>
  );
};

export default SuccessTooltip;
