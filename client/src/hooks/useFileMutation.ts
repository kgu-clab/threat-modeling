import { postFile } from '@api/file';
import { useMutation } from '@tanstack/react-query';

/**
 * JSON 파일을 업로드합니다.
 */
export const useFileMutation = () => {
  const fileMutation = useMutation({
    mutationFn: postFile,
  });

  return { fileMutate: fileMutation.mutate };
};
