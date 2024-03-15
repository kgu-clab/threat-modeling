import { END_POINT } from '@constants/api';
import { server } from './server';
import { BaseResponse } from '@type/api';
import type { ThreatModelType } from '@type/model';

export const getAttacks = async (id: string) => {
  const { data } = await server.get<BaseResponse<ThreatModelType[]>>({
    url: END_POINT.ATTACKS(id),
  });

  return data;
};
