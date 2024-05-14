import { getMainInfo, modifyRandomProblem, modifySuppleProblem } from '@/services/home';
import { APIResponse, IMainInfo } from '@/types/model';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';

export const useHome = () => {
  const queryClient = useQueryClient();

  const useGetMainInfo = () => {
    return useQuery({
      queryKey: ['mainInfo'],
      queryFn: () => getMainInfo(),
      staleTime: Infinity,
      select: (data) => data.data,
    });
  };

  const usePatchRandomProblem = () => {
    return useMutation({
      mutationFn: modifyRandomProblem,
      onSuccess: (data) => {
        queryClient.setQueryData(['mainInfo'], (oldData: APIResponse<IMainInfo>) => {
          return {
            ...oldData,
            data: {
              ...oldData.data,
              randomQuestion: data.data.no,
              randomTitle: data.data.title,
            },
          };
        });
      },
    });
  };

  const usePatchSuppleProblem = () => {
    return useMutation({
      mutationFn: modifySuppleProblem,
      onSuccess: (data) => {
        queryClient.setQueryData(['mainInfo'], (oldData: APIResponse<IMainInfo>) => {
          return {
            ...oldData,
            data: {
              ...oldData.data,
              supplementaryQuestion: data.data.no,
              supplementaryTitle: data.data.title,
            },
          };
        });
      },
    });
  };

  return { useGetMainInfo, usePatchRandomProblem, usePatchSuppleProblem };
};
