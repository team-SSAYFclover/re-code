import { useState } from "react";
import Alarm from "/assets/Alarm.png";

const HomePage = () => {
	const problemNum = useState(0);

	return (
		<>
			<div className="flex flex-col w-full h-[180px] justify-center items-center">
				<div className="w-full h-1/2 flex justify-center items-center gap-4 text-[16px] ">
					<img src={Alarm} alt="" className="w-[40px] h-[40px]" />
					<div>
						오늘의 복습 문제는{" "}
						<span className="text-[#2CDCB2] font-bold">{problemNum}</span>개 입니다.{" "}
						<br />
						<a
							href="https://k10d210.p.ssafy.io"
							className="text-[#53EDC7] underline underline-offset-4"
						>
							re:code로 이동하기
						</a>
					</div>
				</div>
				<div className="w-full h-1/2 flex justify-end items-end p-6">연결해제 버튼</div>
			</div>
		</>
	);
};

export default HomePage;
