import { getProbList, getProbDetail, deleteCode, patchCode } from '@/services/problem';
import { useQuery, useMutation, useInfiniteQuery } from '@tanstack/react-query';
import { IGetProbListParams, ICodePatchParams } from '@/types/problem';
import { APIResponse, IProblemRes } from '@/types/model';

export const useProbList = () => {
  const useGetProbList = (params: IGetProbListParams, isEnabled = false) => {
    return useInfiniteQuery<APIResponse<IProblemRes>>({
      queryKey: ['probList', params],
      queryFn: ({ pageParam = 0 }) => getProbList({ ...params, page: pageParam as number }),
      getNextPageParam: (lastPage) => {
        return lastPage.data.last ? undefined : lastPage.data.number + 1;
      },
      enabled: isEnabled,
      staleTime: Infinity,
      initialPageParam: 0,
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

export const useDeleteCode = () => {
  return useMutation({
    mutationFn: (codeId: number) => deleteCode(codeId),
  });
};

export const usePatchCode = () => {
  return useMutation({
    mutationFn: (variables: { codeId: number; params: ICodePatchParams }) =>
      patchCode(variables.codeId, variables.params),
  });
};
