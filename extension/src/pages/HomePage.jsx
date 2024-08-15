import { useEffect, useState } from 'react';
import Alarm from '/assets/Alarm.png';

const HomePage = () => {
  const [problemNum, setProblemNum] = useState(0);

  const disConnService = () => {
    chrome.storage.local.remove(['id']);
    window.location.reload();
  };

  useEffect(() => {
    chrome.storage.local.get(['id']).then((res) => {
      fetch(`https://re-code.site/api/statistics/${res.id}/reviews/cnt`)
        .then((res) => res.json())
        .then((res) => {
          setProblemNum(res.data);
        });
    });
  }, []);

  return (
    <>
      <div className="flex flex-col w-full h-[150px] justify-center items-center p-5">
        <div className="w-full grow h-1/2 flex justify-center items-start gap-4 text-[16px] ">
          <img src={Alarm} alt="" className="w-[40px] h-[40px]" />
          <div>
            오늘의 복습 문제는 <span className="text-[#2CDCB2] font-bold">{problemNum}</span>개
            입니다. <br />
            <a
              href="https://re-code.site"
              target="_blank"
              className="text-[#53EDC7] underline underline-offset-4"
            >
              re:code로 이동하기
            </a>
          </div>
        </div>
        <button
          className="self-end rounded-md font-bold h-8 text-[14px] border-[1px] border-[#FF9292] bg-[#FF9292] text-white w-20 hover:bg-white hover:text-[#FF9292]"
          onClick={disConnService}
        >
          연동 해제
        </button>
      </div>
    </>
  );
};

export default HomePage;
