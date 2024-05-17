import defaultProfile from '@/assets/default_profile.png';
import Toggle from '@/components/@common/Toggle';
import { useUser } from '@/hooks/user/useUser';
import userStore from '@/stores/userStore';
import { difficultyLevelType } from '@/types/model';
import { useEffect, useState } from 'react';
import { TbCopy } from 'react-icons/tb';
import { Tooltip } from 'react-tooltip';
import Toast from '../@common/Toast';
import TimePicker from './TimePicker';

interface IDifficultyInfo {
  difficulty: difficultyLevelType;
  text: string;
}

export interface IModifyInfo {
  difficulty: difficultyLevelType;
  notificationStatus: boolean;
  notifHour: number;
  notifMinute: number;
}

const MySettingModal = ({ onClose }: { onClose: () => void }) => {
  const { useGetUser, usePatchUser } = useUser();
  const { data } = useGetUser(true);
  const { mutate } = usePatchUser();

  const { name, avatarUrl } = userStore();

  const [modifyInfo, setModifyInfo] = useState<IModifyInfo>({
    difficulty: 1,
    notificationStatus: false,
    notifHour: 0,
    notifMinute: 0,
  });

  const [isModifyDifficulty, setIsModifyDifficulty] = useState<boolean>(false);
  const [isModifyAlarm, setIsModifyAlarm] = useState<boolean>(false);
  const [prevDifficulty, setPrevDifficulty] = useState<difficultyLevelType>(1);
  const [prevAlarmTime, setPrevAlarmTime] = useState<string>('');

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
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const [hour, min, sec] = data.settings.notificationTime.split(':');

      setModifyInfo((prev) => {
        return {
          ...prev,
          difficulty: data.settings.difficulty,
          notificationStatus: data.settings.notificationStatus,
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

  // 알림 수정
  const handleAlarmStatus = () => {
    mutate(
      {
        key: 'notificationStatus',
        value: !modifyInfo.notificationStatus,
      },
      {
        onSuccess: () => {
          setModifyInfo((prev) => {
            return {
              ...prev,
              notificationStatus: !prev.notificationStatus,
            };
          });
        },
      }
    );
  };

  // 복습 난이도 수정
  const handleModifyDifficulty = () => {
    // 저장 상태일때
    if (isModifyDifficulty) {
      mutate(
        {
          key: 'difficulty',
          value: modifyInfo.difficulty,
        },
        {
          onError: () => {
            setModifyInfo((prev) => {
              return {
                ...prev,
                difficulty: prevDifficulty,
              };
            });
          },
        }
      );

      setIsModifyDifficulty(false);
    }
    // 수정 상태일때
    else {
      setIsModifyDifficulty(true);
      setPrevDifficulty(modifyInfo.difficulty);
    }
  };

  const handleCancelModifyDifficulty = () => {
    setModifyInfo((prev) => {
      return {
        ...prev,
        difficulty: prevDifficulty,
      };
    });

    setIsModifyDifficulty(false);
  };

  const handleModifyAlarm = () => {
    // 저장 상태일때
    if (isModifyAlarm) {
      mutate(
        {
          key: 'notificationTime',
          value: `${modifyInfo.notifHour}:${modifyInfo.notifMinute}:00`,
        },
        {
          onError: () => {
            setModifyInfo((prev) => {
              return {
                ...prev,
                notifHour: Number(prevAlarmTime.split(':')[0]),
                notifMinute: Number(prevAlarmTime.split(':')[1]),
              };
            });
          },
        }
      );

      setIsModifyAlarm(false);
    }
    // 수정 상태일때
    else {
      setIsModifyAlarm(true);
      setPrevAlarmTime(`${modifyInfo.notifHour}:${modifyInfo.notifMinute}`);
    }
  };

  const handleCancelModifyAlarm = () => {
    setModifyInfo((prev) => {
      return {
        ...prev,
        notifHour: Number(prevAlarmTime.split(':')[0]),
        notifMinute: Number(prevAlarmTime.split(':')[1]),
      };
    });

    setIsModifyAlarm(false);
  };

  const handleCopyClipBoard = async (text: string) => {
    try {
      await navigator.clipboard.writeText(text);
      Toast.success('연동 코드가 복사되었습니다.');
    } catch (error) {
      Toast.error('복사를 실패했습니다.');
    }
  };

  const modifyBtnClass = 'text-sm leading-6 text-MAIN1';
  const contentCommonClass = 'flex justify-between pt-6 pb-2';
  const textCommonClass = 'text-sm leading-6';

  return (
    <>
      <div className="w-screen h-screen fixed top-0 left-0" onClick={() => onClose()}></div>
      <div className="w-full h-[0px] relative">
        <div className="absolute w-72 right-[20px] bg-white rounded-md shadow-lg p-6 z-20">
          <span className="text-lg">내 정보</span>
          <div className="flex mx-2 border-b-[1px] border-[#E9E9E9] py-2">
            <img
              src={avatarUrl === '' ? defaultProfile : avatarUrl}
              alt="profile"
              className="w-8 mr-2 rounded-full"
            />
            <span>{name}</span>
          </div>
          <div className="pt-2">
            <span className={textCommonClass} data-tooltip-id="extension">
              익스텐션 연동 코드
            </span>
            <Tooltip id="extension">
              <div className="w-60">
                익스텐션을 연결하기 위한 연동 코드입니다. 크롬 익스텐션과 연결하지 않았다면 코드를
                복사하여 연동해주세요.
              </div>
            </Tooltip>
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
            <span className={textCommonClass} data-tooltip-id="level">
              복습 난이도 설정
            </span>
            <Tooltip id="level">
              <div className="w-60">
                난이도 설정을 위한 옵션입니다. <br />
                난이도는 빈칸의 개수, 빈칸의 길이를 기준으로 하여 복습 문제가 출제됩니다.
              </div>
            </Tooltip>
            {!isModifyDifficulty ? (
              <button className={modifyBtnClass} onClick={handleModifyDifficulty}>
                수정
              </button>
            ) : (
              <div className="text-sm">
                <button className="text-gray-300 mr-1" onClick={handleCancelModifyDifficulty}>
                  취소
                </button>
                <button className="text-MAIN1" onClick={handleModifyDifficulty}>
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
            <span className={textCommonClass} data-tooltip-id="alarm">
              알림 설정
            </span>
            <Tooltip id="alarm">
              <div className="w-60">
                알림 수신 옵션입니다. <br />
                알림을 켜지 않았다면 알림을 켜서 리마인드 알림을 받아보세요.
              </div>
            </Tooltip>
            <Toggle isOn={modifyInfo.notificationStatus} handleToggle={handleAlarmStatus} />
          </div>
          {modifyInfo.notificationStatus && (
            <>
              <div className={contentCommonClass}>
                <span className={textCommonClass} data-tooltip-id="time">
                  알림 시간
                </span>
                <Tooltip id="time">
                  <div className="w-60">
                    알림 수신 시간입니다. <br />
                    알림은 하루에 한 번 설정된 시간에 수신할 수 있습니다.
                  </div>
                </Tooltip>
                {!isModifyAlarm ? (
                  <button className={modifyBtnClass} onClick={handleModifyAlarm}>
                    수정
                  </button>
                ) : (
                  <div className="text-sm">
                    <button className="text-gray-300 mr-1" onClick={handleCancelModifyAlarm}>
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
                <TimePicker
                  hour={modifyInfo.notifHour}
                  minute={modifyInfo.notifMinute}
                  setModifyInfo={setModifyInfo}
                />
              )}
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default MySettingModal;
