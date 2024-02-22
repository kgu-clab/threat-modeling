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

  useEffect(() => {
    Dropzone.autoDiscover = false;
    const dropzone = new Dropzone("div.dropzone", {
      url: "/file",
      method: "post",
      autoProcessQueue: false, // 자동 업로드 여부
      addRemoveLinks: true, // 파일 삭제 버튼 표시 여부
      dictRemoveFile: "삭제", // 파일 삭제 버튼 텍스트
      maxFiles: 5, // 업로드 파일 개수
    });
    return () => {
      dropzone.destroy();
    };
  }, []);

  const onClickModeling = () => {
    setModeling(true);
  };
  const onClickCheckbox = () => {
    setIsTermsAccepted(!isTermsAccepted);
  };

  return (
    <Section>
      <div className="flex flex-col items-center p-20 text-center bg-gray-100 border-2 border-dashed dropzone">
        <img src={file} alt="file" className="w-40 m-2" />
      </div>
      <Button className={"mt-2 mb-2 w-fit"} onClick={onClickModeling}>
        {isTermsAccepted ? "Modeling" : "Check Terms"}
      </Button>
      {modeling && (
        <div className="p-2 space-y-2">
          <UploadTerms />
          <div className="items-center space-x-2">
            <Checkbox id="term" onClick={onClickCheckbox} />
            <label>본 약관의 내용을 읽고 이해했으며, 이에 동의합니다.</label>
          </div>
        </div>
      )}
    </Section>
  );
};

export default FileUploaderSection;
