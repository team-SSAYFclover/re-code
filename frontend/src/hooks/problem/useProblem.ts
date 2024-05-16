import Toast from '@/components/@common/Toast';
import { addReview, deleteCode, getProbDetail, getProbList, patchCode } from '@/services/problem';
import { APIResponse, IProblemRes } from '@/types/model';
import { ICodePatchParams, IGetProbListParams } from '@/types/problem';
import { useInfiniteQuery, useMutation, useQuery } from '@tanstack/react-query';

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

export const useAddReview = () => {
  return useMutation({
    mutationFn: (codeId: number) => addReview(codeId),
    onSuccess: () => {
      Toast.success('리스트에 추가되었습니다.');
    },
  });
};
