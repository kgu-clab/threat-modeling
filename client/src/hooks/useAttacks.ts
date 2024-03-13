import { getAttacks } from '@api/attack';
import { QUERY_KEY } from '@constants/key';
import { useQuery } from '@tanstack/react-query';

export const useAttacks = (id: string) => {
  return useQuery({
    queryFn: () => getAttacks(id),
    queryKey: [QUERY_KEY.ATTACKS],
  });
};
