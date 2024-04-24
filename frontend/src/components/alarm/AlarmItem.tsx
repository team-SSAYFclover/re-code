import { useAlarm } from '@/hooks/alarm/useAlarm';
import { transformStringToDate } from '@/utils/transformStringToDate';
import { LuBellRing } from 'react-icons/lu';

const AlarmItem = () => {
  const { useGetAlarm } = useAlarm();
  const { data } = useGetAlarm();

  if (!data) {
    return <div>알림 없음</div>;
  }

  const dateTime = transformStringToDate(data.date);

  return (
    <div className="w-full h-20 flex justify-center items-center">
      <div className="pr-2 flex justify-center items-center">
        <div className="p-2 rounded-full bg-MAIN2 text-MAIN1">
          <LuBellRing size={22} />
        </div>
      </div>
      <div>
        <div className="text-sm">오늘 풀어야 할 복습 문제가 {data.recodecnt}개 남아있어요</div>
        <div className="text-xs text-gray-400">
          {dateTime.year}년 {dateTime.month}월 {dateTime.day}일 {dateTime.hour}:{dateTime.min}
        </div>
      </div>
    </div>
  );
};

export default AlarmItem;
