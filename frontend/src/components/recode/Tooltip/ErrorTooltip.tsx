const ErrorTooltip = ({ input, answer }: { input: string; answer: string }) => {
  if (input.length < answer.length) {
    return (
      <div className="p-2 bg-white">
        <div className="text-[#FF0404] py-1">아쉽게 틀리셨네요. 다시 입력해볼까요?</div>
        <div className="h-10 text-lg bg-[#FFF3F3] px-2 flex justify-center items-center rounded-sm">
          {input.split('').map((x, idx) => {
            return (
              <span
                key={idx}
                className={`${answer[idx] === x ? 'text-[#6A6A6A]' : 'text-red-600'}`}
              >
                {x}
              </span>
            );
          })}
          <span>{Array.from({ length: answer.length - input.length }, () => '_').join(' ')}</span>
        </div>
      </div>
    );
  }

  return (
    <div className="p-2 bg-white">
      <div className="text-[#FF0404] py-1">아쉽게 틀리셨네요. 다시 입력해볼까요?</div>
      <div className="h-10 text-lg bg-[#FFF3F3] flex justify-center items-center">
        {input.split('').map((x, idx) => {
          return (
            <span key={idx} className={`${answer[idx] === x ? 'text-[#6A6A6A]' : 'text-red-600'}`}>
              {x}
            </span>
          );
        })}
      </div>
    </div>
  );
};

export default ErrorTooltip;
