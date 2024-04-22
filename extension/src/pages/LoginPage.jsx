const LoginPage = () => {
	return (
		<>
			<div className="flex flex-col w-full h-[240px] justify-center items-center">
				<div className="grow w-full flex justify-center items-center">
					<div>깃허브 OAuth 버튼</div>
				</div>
				<div className="w-full">
					<p className="text-center text-[16px]">
						<a href="https://k10d210.p.ssafy.io" className="text-[#5A8AF2]">
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
