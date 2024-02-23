import Content from "@/components/common/Content/Content";
import BannerSection from "@/components/main/BannerSection/BannerSection";
import ExplainSection from "@/components/main/ExplainSection/ExplainSection";
import FileUploaderSection from "@/components/main/FileUploaderSection/FileUploaderSection";

const MainPage = () => {
  return (
    <Content>
      <BannerSection />
      <ExplainSection />
      <FileUploaderSection />
    </Content>
  );
};

export default MainPage;
