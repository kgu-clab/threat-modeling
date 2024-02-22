import Content from "@/components/common/Content/Content";
import ExplainSection from "@/components/main/ExplainSection/ExplainSection";
import FileUploaderSection from "@/components/main/FileUploaderSection/FileUploaderSection";

const MainPage = () => {
  return (
    <Content>
      <ExplainSection />
      <FileUploaderSection />
    </Content>
  );
};

export default MainPage;
