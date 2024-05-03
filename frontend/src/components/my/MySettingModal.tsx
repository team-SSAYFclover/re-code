import defaultProfile from '@/assets/default_profile.png';
import Toggle from '@/components/@common/Toggle';
import { useUser } from '@/hooks/user/useUser';
import userStore from '@/stores/userStore';
import { difficultyLevelType } from '@/types/model';
import { useEffect, useState } from 'react';
import { TbCopy } from 'react-icons/tb';
import Toast from '../@common/Toast';
import TimePicker from './TimePicker';
interface IDifficultyInfo {
  difficulty: difficultyLevelType;
  text: string;
}

interface IModifyInfo {
  difficulty: difficultyLevelType;
  notifStatus: boolean;
  notifHour: number;
  notifMinute: number;
}

const MySettingModal = ({ onClose }: { onClose: () => void }) => {
  const { useGetUser } = useUser();
  const { data } = useGetUser();
  const { name, avatarUrl } = userStore();

  console.log(data);

  const [modifyInfo, setModifyInfo] = useState<IModifyInfo>({
    difficulty: 1,
    notifStatus: false,
    notifHour: 0,
    notifMinute: 0,
  });

  const [isModifyDifficulty, setIsModifyDifficulty] = useState<boolean>(false);
  const [isModifyAlarm, setIsModifyAlarm] = useState<boolean>(false);

  const difficultyBtns: IDifficultyInfo[] = [
    {
      difficulty: 3,
      text: '상',
    },
    {
      difficulty: 2,
      text: '중',
    },
    {
      difficulty: 1,
      text: '하',
    },
  ];

  useEffect(() => {
    if (data) {
      const [hour, min, _] = data.settings.notificationTime.split(':');

      setModifyInfo((prev) => {
        return {
          ...prev,
          difficulty: data.settings.difficulty,
          notifStatus: data.settings.notificationStatus,
          notifHour: Number(hour),
          notifMinute: Number(min),
        };
      });
    }
  }, [data]);

  const handleDifficult = (difficulty: difficultyLevelType) => {
    if (!isModifyDifficulty) return;

    setModifyInfo((prev) => {
      return {
        ...prev,
        difficulty: difficulty,
      };
    });
  };

  const handleAlarmStatus = () => {
    // @TODO: api 호출 결과 성공이면 바꾸기
    setModifyInfo((prev) => {
      return {
        ...prev,
        notifStatus: !prev.notifStatus,
      };
    });
  };

  const handleModifyLevel = () => {
    // @TODO : 복습 난이도 정보 저장하는 api 호출
    if (isModifyDifficulty) {
      setIsModifyDifficulty(false);
    } else {
      setIsModifyDifficulty(true);
    }
  };

  const handleModifyAlarm = () => {
    // @TODO: 수정 중인 상태에서 누르면 알림 시간을 저장하는 api 호출
    if (isModifyAlarm) {
      setIsModifyAlarm(false);
    } else {
      setIsModifyAlarm(true);
    }
  };

  const modifyBtnClass = 'text-sm leading-6 text-MAIN1';
  const contentCommonClass = 'flex justify-between pt-6 pb-2';
  const textCommonClass = 'text-sm leading-6';

  const handleCopyClipBoard = async (text: string) => {
    try {
      await navigator.clipboard.writeText(text);
      Toast.success('연동 코드가 복사되었습니다.');
    } catch (error) {
      Toast.error('복사를 실패했습니다.');
    }
  };

  return (
    <>
      <div className="w-screen h-screen fixed top-0 left-0" onClick={() => onClose()}></div>
      <div className="absolute top-16 right-4 w-72 bg-white rounded-md shadow-lg p-6 z-20">
        <span className="text-lg">내 정보</span>
        <div className="flex mx-2 border-b-[1px] border-[#E9E9E9] py-2">
          <img
            src={avatarUrl === '' ? defaultProfile : avatarUrl}
            alt="profile"
            className="w-8 mr-2"
          />
          <span>{name}</span>
        </div>
        <div className="pt-2">
          <span className={textCommonClass}>익스텐션 연동 코드</span>
          <div className="text-sm bg-[#F3F3F3] text-[#5A5A5A] py-3 my-1 rounded-md grid grid-cols-7 place-items-center">
            <div className="col-span-1"></div>
            <span className="col-span-5 break-all">{data?.uuid}</span>
            <div
              className="col-span-1 cursor-pointer"
              onClick={() => handleCopyClipBoard(data?.uuid || '')}
            >
              <TbCopy />
            </div>
          </div>
        </div>
        <div className={contentCommonClass}>
          <span className={textCommonClass}>복습 난이도 설정</span>
          {!isModifyDifficulty ? (
            <button className={modifyBtnClass} onClick={handleModifyLevel}>
              수정
            </button>
          ) : (
            <div className="text-sm">
              <button className="text-gray-300 mr-1" onClick={() => setIsModifyDifficulty(false)}>
                취소
              </button>
              <button className="text-MAIN1" onClick={handleModifyLevel}>
                저장
              </button>
            </div>
          )}
        </div>
        {/* 버튼 */}
        <div className="grid grid-cols-3 gap-2">
          {difficultyBtns.map((btn, idx) => {
            return (
              <button
                className={`rounded-md font-bold h-8 ${modifyInfo.difficulty === btn.difficulty ? 'bg-MAIN1 text-MAIN2' : 'bg-MAIN2 text-MAIN1'}`}
                key={idx}
                onClick={() => handleDifficult(btn.difficulty)}
              >
                {btn.text}
              </button>
            );
          })}
        </div>

        <div className={contentCommonClass}>
          <span className={textCommonClass}>알림 설정</span>
          <Toggle isOn={modifyInfo.notifStatus} handleToggle={handleAlarmStatus} />
        </div>
        {modifyInfo.notifStatus && (
          <>
            <div className={contentCommonClass}>
              <span className={textCommonClass}>알림 시간</span>
              {!isModifyAlarm ? (
                <button className={modifyBtnClass} onClick={handleModifyAlarm}>
                  수정
                </button>
              ) : (
                <div className="text-sm">
                  <button className="text-gray-300 mr-1" onClick={() => setIsModifyAlarm(false)}>
                    취소
                  </button>
                  <button className="text-MAIN1" onClick={handleModifyAlarm}>
                    저장
                  </button>
                </div>
              )}
            </div>
            {!isModifyAlarm ? (
              <div className="text-right">
                {modifyInfo.notifHour}:
                {Number(modifyInfo.notifMinute) < 10
                  ? '0' + Number(modifyInfo.notifMinute)
                  : Number(modifyInfo.notifMinute)}
                &nbsp;
                {Number(modifyInfo.notifHour) < 12 ? 'AM' : 'PM'}
              </div>
            ) : (
              <TimePicker hour={10} minute={0} />
            )}
          </>
        )}
      </div>
    </>
  );
};

export default MySettingModal;
