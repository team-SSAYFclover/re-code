import { Dispatch, SetStateAction, useState } from 'react';
import { IModifyInfo } from './MySettingModal';

interface ITimePickerProps {
  hour: number;
  minute: number;
  setModifyInfo: Dispatch<SetStateAction<IModifyInfo>>;
}

interface ISelectedTime {
  hour: number;
  minute: number;
  type: 'AM' | 'PM';
}

const TimePicker = ({ hour, minute, setModifyInfo }: ITimePickerProps) => {
  const hours = Array.from({ length: 12 }, (_, idx) => idx);
  const mins = Array.from({ length: 4 }, (_, idx) => idx * 15);

  const [selectedTime, setSelectedTime] = useState<ISelectedTime>({
    hour: hour,
    minute: minute,
    type: hour > 12 ? 'PM' : 'AM',
  });

  const handleTime = (type: 'hour' | 'minute' | 'type', value: string) => {
    setSelectedTime((prev) => {
      return {
        ...prev,
        [type]: value,
      };
    });

    if (type === 'hour') {
      setModifyInfo((prev) => {
        return {
          ...prev,
          notifHour: selectedTime.type === 'PM' ? 12 + Number(value) : Number(value),
        };
      });
    } else if (type === 'minute') {
      setModifyInfo((prev) => {
        return {
          ...prev,
          notifMinute: Number(value),
        };
      });
    } else if (type === 'type') {
      if (value === 'AM') {
        if (selectedTime.type === 'PM') {
          setModifyInfo((prev) => {
            return {
              ...prev,
              notifHour: Number(prev.notifHour) - 12,
            };
          });
        }
      } else {
        if (selectedTime.type === 'AM') {
          setModifyInfo((prev) => {
            return {
              ...prev,
              notifHour: Number(prev.notifHour) + 12,
            };
          });
        }
      }
    }
  };

  const inputCommonClass =
    'w-16 h-14 px-1 outline-MAIN1 text-[25px] text-center bg-gray-100 mb-2 rounded-md';

  return (
    <div className="flex justify-between items-center px-4 py-3 rounded-sm">
      <div>
        <select
          value={selectedTime.hour >= 12 ? selectedTime.hour - 12 : selectedTime.hour}
          className={inputCommonClass}
          onChange={(e) => handleTime('hour', e.target.value)}
        >
          {hours.map((ele, idx) => {
            return (
              <option className="text-gray-400 text-md" value={ele} key={idx}>
                {ele}
              </option>
            );
          })}
        </select>
        <div className="text-sm text-gray-400 pl-1">시</div>
      </div>
      <div className="text-[30px] h-20 text-gray-400">:</div>
      <div>
        <select
          value={selectedTime.minute}
          className={inputCommonClass}
          onChange={(e) => handleTime('minute', e.target.value)}
        >
          {mins.map((ele, idx) => {
            return (
              <option className="text-gray-400 text-md" value={ele} key={idx}>
                {ele}
              </option>
            );
          })}
        </select>
        <div className="text-sm text-gray-400 pl-1">분</div>
      </div>
      <div className="w-12 h-20">
        <div className="flex flex-col border border-gray-200 rounded-sm text-gray-500">
          <button
            className={`h-7 ${selectedTime.type === 'AM' && 'bg-MAIN2 text-MAIN1'}`}
            onClick={() => handleTime('type', 'AM')}
          >
            AM
          </button>
          <button
            className={`h-7 ${selectedTime.type === 'PM' && 'bg-MAIN2 text-MAIN1'}`}
            onClick={() => handleTime('type', 'PM')}
          >
            PM
          </button>
        </div>
      </div>
    </div>
  );
};

export default TimePicker;
