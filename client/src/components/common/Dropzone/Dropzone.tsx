import { useCallback, useState } from 'react';
import { useDropzone } from 'react-dropzone';
import { BsFiletypeJson } from 'react-icons/bs';
import { BsFileEarmarkArrowUp } from 'react-icons/bs';
import { Button } from '../Button/Button';
import { useTranslation } from 'react-i18next';
import { toast } from 'react-hot-toast';
import type { AttackFlowJsonType } from '@type/file';
import { LuLoader2 } from 'react-icons/lu';
import { useNavigate } from 'react-router-dom';
import { PATH_FINDER } from '@constants/path';
import { obfuscate } from '@utils/string';
import { parserAttackFlow } from '@utils/model';
import { useFileMutation } from '@hooks/useFileMutation';

const Dropzone = () => {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const { fileMutate } = useFileMutation();

  const [isLoading, setIsLoading] = useState<boolean>(false);

  const onDrop = useCallback(
    (acceptedFiles: File[]) => {
      const originFile = acceptedFiles[0];

      if (!originFile) {
        // 파일이 없을 경우, 에러 메시지 출력
        return toast.error(t('fileUploadError'));
      } else {
        const reader = new FileReader();

        reader.onload = () => {
          const fileText = reader.result;

          try {
            const json: AttackFlowJsonType = JSON.parse(fileText as string);
            // Attack Flow JSON 파일 형식 검증
            if (json.type !== 'bundle') {
              return toast.error(t('fileUploadError'));
            } else {
              setIsLoading(true);
              toast.success(t('fileUploadSuccess'));
            }
            // 파일에서 분석 데이터 추출
            const techniqueIDList = Array.from(
              new Set(
                json.objects
                  .filter((object) => object.type === 'attack-action')
                  .map((object) => object.technique_id)
                  .sort(),
              ),
            );
            // 분석 파일을 서버에 저장
            fileMutate(originFile);
            // 분석 데이터를 암호화하여 URL로 전달, 분석 결과 페이지로 이동
            navigate(PATH_FINDER.RESULT(obfuscate(techniqueIDList.join(','))), {
              state: {
                data: parserAttackFlow(json),
              },
            });
          } catch (error) {
            toast.error(t('fileUploadReadError'));
          }
        };
        reader.readAsText(originFile);
      }
    },
    [fileMutate, navigate, t],
  );

  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
    maxFiles: 1,
    multiple: false,
    accept: { 'application/json': [] },
  });

  return (
    <div
      className="flex items-center justify-center text-gray-600 border-2 border-dashed rounded-lg cursor-pointer min-h-56 bg-gray-50"
      {...getRootProps()}
    >
      {isLoading ? (
        <div className="flex flex-col items-center justify-center w-full text-center">
          <LuLoader2 size={32} className="m-2 animate-spin" />
          <p>{t('loading')}</p>
        </div>
      ) : (
        <>
          <input {...getInputProps()} />
          <div className="flex flex-col items-center justify-center gap-2 px-4 text-sm text-center break-keep">
            {isDragActive ? (
              <>
                <BsFileEarmarkArrowUp size={46} />
                <p>{t('fileDragAndDrop')}</p>
              </>
            ) : (
              <>
                <BsFiletypeJson size={46} />
                <p>{t('fileUploadDescription')}</p>
                <p>{t('or')}</p>
                <Button>{t('fileUploadButton')}</Button>
              </>
            )}
          </div>
        </>
      )}
    </div>
  );
};

export default Dropzone;
