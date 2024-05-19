import Toast from '@/components/@common/Toast';
import { getUserInfo, modifyUserInfo } from '@/services/user';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';

export const useUser = () => {
  const queryClient = useQueryClient();

  const useGetUser = (isTokenStored: boolean) => {
    return useQuery({
      queryKey: ['my'],
      queryFn: () => getUserInfo(),
      staleTime: Infinity,
      select: (data) => data.data,
      enabled: isTokenStored,
    });
  };

  const usePatchUser = () => {
    return useMutation({
      mutationFn: ({ key, value }: { key: string; value: string | boolean | number }) =>
        modifyUserInfo(key, value),
      onSuccess: () => {
        Toast.success('수정되었습니다.');
        queryClient.invalidateQueries({ queryKey: ['my'] });
      },
      onError: () => {
        Toast.error('수정하지 못했습니다.');
      },
    });
  };

  return { useGetUser, usePatchUser };
};
