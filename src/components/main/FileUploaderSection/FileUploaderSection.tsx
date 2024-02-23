import Dropzone from "dropzone";
import Section from "../../common/Section/Section";
import { useEffect, useState } from "react";
import { Button } from "@/components/common/Button/Button";
import file from "/src/assets/explain/file.svg";
import UploadTerms from "../UploadClause/UploadTerms";
import { Checkbox } from "@/components/common/CheckBox/CheckBox";

const FileUploaderSection = () => {
  const [modeling, setModeling] = useState(false);
  const [isTermsAccepted, setIsTermsAccepted] = useState(false);
  const [showTerm, setShowTerm] = useState(false);
  useEffect(() => {
    Dropzone.autoDiscover = false;
    const dropzone = new Dropzone("div.dropzone", {
      url: "url", // 파일 업로드 서버 url
      method: "post",
      headers: {
        // 요청 헤더
        // Authorization: 'Bearer ' + token,
      },
      autoProcessQueue: false, // 자동 업로드 여부
      addRemoveLinks: true, // 파일 삭제 버튼 표시 여부
      dictRemoveFile: "삭제", // 파일 삭제 버튼 텍스트
      createImageThumbnails: false, // 파일 썸네일 여부
      acceptedFiles: "application/json", // 입력 가능 파일 타입
      dictInvalidFileType: "JSON 파일을 업로드 해주세요", // 입력 가능 파일 타입과 다른 타입의 파일 업로드 시
      uploadMultiple: false, // 다중 파일 업로드 여부

      init: function () {
        this.on("addedfile", () => {
          if (modeling) {
            this.processQueue();
          }
        });
      },
    });
    return () => {
      dropzone.destroy();
    };
  }, []);

  const onClickModeling = () => {
    if (!showTerm) setShowTerm(!showTerm);
    else isTermsAccepted && setModeling(true);
  };

  const onClickCheckbox = () => {
    setIsTermsAccepted(!isTermsAccepted);
  };

  return (
    <Section className="pb-20 border-none lg:px-36">
      <div
        className="flex flex-col items-center p-8 bg-gray-100 border-2 border-dashed dropzone"
        id="fileDrop"
      >
        <img src={file} alt="file" className="w-40 m-2" />
      </div>
      <Button className={"mt-6 w-fit"} onClick={onClickModeling}>
        {isTermsAccepted ? "Modeling" : "Check Terms"}
      </Button>
      {showTerm && (
        <div className="mt-6 space-y-2">
          <UploadTerms />
          <div className="iteㄴms-center space-x-2">
            <Checkbox id="term" onClick={onClickCheckbox} />
            <label>본 약관의 내용을 읽고 이해했으며, 이에 동의합니다.</label>
          </div>
        </div>
      )}
    </Section>
  );
};

export default FileUploaderSection;
