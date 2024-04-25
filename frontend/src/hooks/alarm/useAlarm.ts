import { getTodayAlarm } from '@/services/alarm';
import { useQuery } from '@tanstack/react-query';

export const useAlarm = () => {
  const useGetAlarm = () => {
    return useQuery({
      queryKey: ['alarm'],
      queryFn: () => getTodayAlarm(),
      staleTime: Infinity,
      select: (data) => data.data,
    });
  };

  return { useGetAlarm };
};
