import { getAttacks } from '@api/attack';
import { QUERY_KEY } from '@constants/key';
import { useSuspenseQuery } from '@tanstack/react-query';

/**
 * Attack 관련 정보를 조회합니다.
 */
export const useAttacks = (id: string) => {
  return useSuspenseQuery({
    queryFn: () => getAttacks(id),
    queryKey: [QUERY_KEY.ATTACKS],
  });
};
