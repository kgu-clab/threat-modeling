import { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';
import { BsFiletypeJson } from 'react-icons/bs';
import { BsFileEarmarkArrowUp } from 'react-icons/bs';
import { Button } from '../Button/Button';

const Dropzone = () => {
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
      <div className="flex flex-col items-center justify-center gap-2 text-sm">
        {isDragActive ? (
          <>
            <BsFileEarmarkArrowUp size={46} />
            <p>파일이 인식됐어요, 지금 놓으시면 돼요!</p>
          </>
        ) : (
          <>
            <BsFiletypeJson size={46} />
            <p>드래그 앤 드롭으로 파일을 여기에 놓아주세요.</p>
            <p>또는</p>
            <Button>업로드 파일 선택하기</Button>
          </>
        )}
      </div>
    </div>
  );
};

export default Dropzone;
