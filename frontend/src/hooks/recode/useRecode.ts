import { getRecode, getTodayRecodeList } from '@/services/recode';
import { useQuery } from '@tanstack/react-query';

export const useRecode = () => {
  const useGetTodayRecodeList = () => {
    return useQuery({
      queryKey: ['todayRecode'],
      queryFn: () => getTodayRecodeList(),
      select: (data) => data.data.todayProblems,
    });
  };

  const useGetRecode = (codeId: string) => {
    return useQuery({
      queryKey: ['recode', codeId],
      queryFn: () => getRecode(codeId),
      select: (data) => data.data,
    });
  };

  return { useGetTodayRecodeList, useGetRecode };
};
