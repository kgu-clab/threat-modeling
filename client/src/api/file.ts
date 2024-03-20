import { END_POINT } from '@constants/api';
import { server } from './server';
import { FILE_KEY } from '@constants/key';
import type { BaseResponse } from '@type/api';

/**
 * JSON 업로드
 */
export const postFile = async (json: File) => {
  const formData = new FormData();
  formData.append(FILE_KEY, json);

  const { data } = await server.post<FormData, BaseResponse<string[]>>({
    url: END_POINT.FILES,
    body: formData,
  });

  return data;
};
