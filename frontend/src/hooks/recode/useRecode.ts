import Toast from '@/components/@common/Toast';
import { completeRecode, getRecode, getTodayRecodeList } from '@/services/recode';
import { useMutation, useQuery } from '@tanstack/react-query';

export const useRecode = () => {
  const useGetTodayRecodeList = () => {
    return useQuery({
      queryKey: ['todayRecode'],
      queryFn: () => getTodayRecodeList(),
      select: (data) => data.data,
    });
  };

  const useGetRecode = (codeId: string) => {
    return useQuery({
      queryKey: ['recode', codeId],
      queryFn: () => getRecode(codeId),
      select: (data) => data.data,
    });
  };

  const usePutRecode = (codeId: string) => {
    return useMutation({
      mutationFn: () => completeRecode(codeId),
      onSuccess: () => {
        Toast.success('복습을 완료했습니다.');
      },
      onError: () => {
        Toast.error('복습을 완료하지 못했습니다.');
      },
    });
  };

  return { useGetTodayRecodeList, useGetRecode, usePutRecode };
};
