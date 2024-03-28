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
import TermsOfUseModal from '@components/result/TermsOfUseModal/TermsOfUseModal';
import { LOCAL_STORAGE_KEY } from '@constants/key';
import { ENVIRONMENT_MODE } from '@constants/common';

const Dropzone = () => {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const { fileMutate } = useFileMutation();
  const isAgree = localStorage.getItem(LOCAL_STORAGE_KEY.AGREE) ? true : false;

  const [showTerms, setShowTerms] = useState<boolean>(false);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const handleShowTerms = useCallback(() => {
    if (!isAgree) setShowTerms(true);
  }, [isAgree]);

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
            }
            setIsLoading(true);
            // 파일에서 분석 데이터 추출
            const techniqueIDList = Array.from(
              new Set(
                json.objects
                  .filter((object) => object.type === 'attack-action')
                  .map((object) => object.technique_id)
                  .sort(),
              ),
            );
            // 개발환경일 경우 분석 파일을 서버에 저장
            if (ENVIRONMENT_MODE === 'production') {
              fileMutate(originFile);
            }
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
    onDrop, // 파일 이벤트
    maxFiles: 1, // 최대 업로드 파일은 1개로 제한
    multiple: false, // 단일 파일만 허용
    accept: { 'application/json': [] },
    disabled: isLoading || showTerms || !isAgree, // 파일 업로드 중, 약관 미동의 시 비활성화
  });

  return (
    <>
      <TermsOfUseModal isOpen={showTerms} setIsOpen={setShowTerms} />
      <div onClick={handleShowTerms}>
        <div
          className="flex items-center justify-center text-gray-600 border-2 border-dashed rounded-lg cursor-pointer min-h-56 bg-gray-50 dark:bg-transparent dark:text-gray-300"
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
      </div>
    </>
  );
};

export default Dropzone;
