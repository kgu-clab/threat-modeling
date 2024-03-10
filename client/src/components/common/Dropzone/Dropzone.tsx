import { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';
import { BsFiletypeJson } from 'react-icons/bs';
import { BsFileEarmarkArrowUp } from 'react-icons/bs';
import { Button } from '../Button/Button';
import { useTranslation } from 'react-i18next';

const Dropzone = () => {
  const { t } = useTranslation();

  const onDrop = useCallback((acceptedFiles: File[]) => {
    console.log(acceptedFiles);
  }, []);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div
      className="flex items-center justify-center text-gray-600 border-2 border-dashed rounded-lg cursor-pointer min-h-56 bg-gray-50"
      {...getRootProps()}
    >
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
    </div>
  );
};

export default Dropzone;
