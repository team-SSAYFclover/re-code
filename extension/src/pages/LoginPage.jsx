import { useRef, useState } from "react";

const LoginPage = () => {
  const uuidRef = useRef();
  const [text, setText] = useState("");

  const connService = () => {
    fetch(`${import.meta.env.VITE_BASE_URL}/users/code?uuid=${uuidRef.current.value}`)
      .then((res) => res.json())
      .then((res) => {
        chrome.storage.local.set({ githubId: res.data.githubId }).then(() => {
          console.log("set Chome");
        });
        window.location.reload();
      })
      .catch(() => {
        setText("잘못된 코드입니다.");
      });
  };

  return (
    <>
      <div className="flex flex-col w-full h-[240px] justify-center items-center">
        <div className="grow flex flex-col justify-center items-center gap-4 w-2/3">
          <div className="flex w-full">
            <div className="font-bold text-[20px]">연동 코드 입력</div>
          </div>
          <input type="text" className="bg-slate-200 rounded-md h-10 w-full p-4" ref={uuidRef} />

          <div className="flex w-full justify-between">
            <div>{text}</div>
            <button
              className="rounded-md font-bold h-8 text-[14px] bg-MAIN1 text-MAIN2 w-20"
              onClick={connService}
            >
              연동
            </button>
          </div>
        </div>
        <div className="w-full">
          <p className="text-center text-[14px]">
            <a href="https://www.recode-d210.com" className="text-[#5A8AF2]">
              여기
            </a>
            를 눌러 회원가입하러가기
          </p>
        </div>
      </div>
    </>
  );
};

export default LoginPage;
