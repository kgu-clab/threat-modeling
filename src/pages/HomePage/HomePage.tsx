import Content from '@components/common/Content/Content';
import ExplainSection from '@components/home/ExplainSection/ExplainSection';
import FileUploaderSection from '@components/home/FileUploaderSection/FileUploaderSection';

const HomePage = () => {
  return (
    <Content>
      <div className="flex flex-col justify-center h-56 text-center bg-gray-200">
        <h1 className="mb-4 text-4xl font-bold text-gray-700">
          Threat Modeling
        </h1>
        <h2 className="text-gray-600">
          체계적이고 정확한 보안 취약성 분석과 대응 방안을 제공힙니다.
        </h2>
      </div>
      <ExplainSection />
      <FileUploaderSection />
    </Content>
  );
};

export default HomePage;
