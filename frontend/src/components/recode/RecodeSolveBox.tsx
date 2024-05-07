const RecodeSolveBox = ({
  codeParts,
  inputs,
  handleInputChange,
  answer,
}: {
  codeParts: string[];
  inputs: string[];
  handleInputChange: (idx: number, value: string) => void;
  answer: string[];
}) => {
  return (
    <pre className="whitespace-pre-wrap break-keep">
      {codeParts.map((part, idx) => (
        <span key={idx}>
          <span>{part}</span>
          {idx < codeParts.length - 1 && (
            <input
              type="text"
              value={inputs[idx]}
              onChange={(e) => handleInputChange(idx, e.target.value)}
              className="bg-MAIN1/20 inline-block my-1 p-1 outline-MAIN1"
              style={{ width: `${answer[idx].length * 16}px` }}
            />
          )}
        </span>
      ))}
    </pre>
  );
};

export default RecodeSolveBox;
