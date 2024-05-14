import { getProbList, getProbDetail, deleteCode, patchCode } from '@/services/problem';
import { useQuery, useMutation } from '@tanstack/react-query';
import { IGetProbListParams, ICodePatchParams } from '@/types/problem';

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
