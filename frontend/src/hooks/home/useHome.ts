import { getMainInfo } from '@/services/home';
import { useQuery } from '@tanstack/react-query';

export const useHome = () => {
  const useGetMainInfo = () => {
    return useQuery({
      queryKey: ['mainInfo'],
      queryFn: () => getMainInfo(),
      staleTime: Infinity,
      select: (data) => data.data,
    });
  };

  return { useGetMainInfo };
};
