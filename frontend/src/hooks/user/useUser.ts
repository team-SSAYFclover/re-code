import { getUserInfo } from '@/services/user';
import { useQuery } from '@tanstack/react-query';

export const useUser = () => {
  const useGetUser = () => {
    return useQuery({
      queryKey: ['my'],
      queryFn: () => getUserInfo(),
      staleTime: Infinity,
      select: (data) => data.data,
    });
  };

  return { useGetUser };
};
