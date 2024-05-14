import logo from '@/assets/logo.png';
import logo2 from '@/assets/logo2.png';
import { motion } from 'framer-motion';
import { FaRegArrowAltCircleDown } from 'react-icons/fa';
interface IGuideDetail {
  title: JSX.Element;
  desc: JSX.Element;
}

const GuidePage = () => {
  const guide: string[] = [
    're:code 홈페이지에 들어가 github 로그인을 진행합니다.',
    'chrome 웹스토어에서 re:code를 검색하여 extension을 다운로드 합니다.',
    're:code 홈페이지에서 내 연동 코드를 복사하여 익스텐션에 연동합니다.',
    'BOJ에서 문제를 풀고 맞았다면 re:code 아이콘을 눌러 복습 문제에 추가합니다.',
    're:code의 오늘의 복습 리스트에 등록되면 문제를 풀어 복습을 진행합니다.',
  ];

  const guideDetail: IGuideDetail[] = [
    {
      title: (
        <div>
          <span className="text-MAIN1">re:code</span>에서{' '}
          <span className="text-MAIN1">github 로그인</span>을 진행
        </div>
      ),
      desc: <>re:code 사이트에 들어가 github 버튼을 눌러 로그인을 진행해 주세요</>,
    },
    {
      title: (
        <div>
          <span className="text-MAIN1">chrome extension</span>을 다운로드
        </div>
      ),
      desc: (
        <div className="text-center">
          re:code 사이트에 복습 문제를 업로드 하기 위한 chrome extension을 다운로드 합니다. <br />
          chrome 웹스토어에서 re:code를 검색 후 다운로드 합니다.
        </div>
      ),
    },
    {
      title: (
        <div>
          <span className="text-MAIN1">extension</span>을 re:code와 연동
        </div>
      ),
      desc: (
        <div className="text-center">
          re:code사이트의 내 정보에서 연동 코드를 복사 후 extension에 입력하여 연동합니다.
        </div>
      ),
    },
    {
      title: (
        <div>
          맞은 문제를 <span className="text-MAIN1">re:code 아이콘</span>을 눌러 복습 문제를 추가
        </div>
      ),
      desc: (
        <div className="text-center">
          re:code 사이트에 복습 문제를 업로드 하기 위한 chrome extension을 다운로드 합니다. <br />
          chrome 웹스토어에서 re:code를 검색 후 다운로드 합니다.
        </div>
      ),
    },
    {
      title: (
        <div>
          <span className="text-MAIN1">오늘의 복습 리스트</span>에 등록된 문제를 복습
        </div>
      ),
      desc: (
        <div className="text-center">
          re:code의 오늘의 복습 리스트에 등록되면 문제를 풀어 복습을 진행합니다. <br />
          빈칸을 모두 채우면 복습 완료를 눌러 복습을 마무리합니다.
        </div>
      ),
    },
  ];

  const titleCommonClass = 'text-[32px] font-black py-2 flex items-center';

  const varient = {
    hidden: { opacity: 0, y: 30 },
    visible: { opacity: 1, y: 0, transition: { duration: 1, delay: 0.2 } },
  };

  return (
    <div className="w-full h-auto bg-gradient-to-b from-[#F7FFFD] from-0% to-[#BBFFEE] to-100%">
      <div className="w-[70%] mx-auto">
        <motion.section
          variants={varient}
          initial="hidden"
          whileInView="visible"
          className="w-full py-16 flex flex-col items-center"
        >
          <div className="text-MAIN1 text-[20px] font-bold">USER GUIDE</div>
          <div className={titleCommonClass}>
            <img alt="logo" src={logo} className="w-32" />가 처음이라면?
          </div>
          <span>시작 가이드를 통해 re:code를 하나씩 알아가보아요</span>
          <img
            className="w-full h-full bg-gray-100 mt-10 rounded-md shadow-lg"
            alt="site"
            src="src/assets/guide/main.webp"
          />
        </motion.section>
        <motion.section variants={varient} initial="hidden" whileInView="visible" className="my-16">
          <div className={titleCommonClass}>
            <img alt="logo" src={logo2} className="w-32" />를 이용하는 순서
          </div>
          {/* 순서 박스 */}
          <div className="w-full bg-white rounded-xl border border-MAIN1 p-10">
            {guide.map((text, idx) => (
              <motion.div
                key={idx}
                className="flex flex-col items-center"
                variants={varient}
                initial="hidden"
                whileInView="visible"
              >
                <div key={idx} className="font-semibold text-[18px]">
                  {text}
                </div>
                {idx < guide.length - 1 && (
                  <FaRegArrowAltCircleDown
                    color="53EDC7"
                    size={26}
                    style={{ marginTop: '12px', marginBottom: '12px' }}
                  />
                )}
              </motion.div>
            ))}
          </div>
        </motion.section>
        <motion.section variants={varient} initial="hidden" whileInView="visible" className="my-16">
          {guideDetail.map((info, idx) => {
            return (
              <motion.article
                variants={varient}
                initial="hidden"
                whileInView="visible"
                key={idx}
                className="my-12 py-12 flex flex-col items-center"
              >
                <div className="px-3 py-1 rounded-full border-[2.3px] border-MAIN1 text-MAIN1 font-bold">
                  STEP 0{idx + 1}
                </div>
                <div className="py-2 text-[30px] font-black">{info.title}</div>
                <div>{info.desc}</div>
                <img
                  src={`src/assets/guide/guide${idx + 1}.webp`}
                  alt="guide"
                  className="w-full h-full bg-gray-100 mt-10 rounded-md shadow-lg"
                />
              </motion.article>
            );
          })}
        </motion.section>
      </div>
    </div>
  );
};

export default GuidePage;
