import { getProbList, getProbDetail } from '@/services/problem';
import { useQuery } from '@tanstack/react-query';
import { IGetProbListParams } from '@/types/problem';

export const useProbList = () => {
  const useGetProbList = (params: IGetProbListParams, isEnabled = false) => {
    return useQuery({
      queryKey: ['probList', params],
      queryFn: () => getProbList(params),
      enabled: isEnabled,
      staleTime: Infinity,
      select: (data) => data.data,
    });
  };

  return { useGetProbList };
};

export const useProbDetail = () => {
  const useGetProbDetail = (problemNo: number) => {
    return useQuery({
      queryKey: ['probDetail', problemNo],
      queryFn: () => getProbDetail(problemNo),
      staleTime: Infinity,
      select: (data) => data.data,
    });
  };
  return { useGetProbDetail };
};
