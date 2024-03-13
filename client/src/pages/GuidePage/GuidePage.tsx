import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { BlockMapType, NotionRenderer } from 'react-notion';

const GuidePage = () => {
  const { t } = useTranslation();
  const [data, setData] = useState<BlockMapType>({});

  useEffect(() => {
    const NOTION_PAGE_ID = '94157834e2404a4c821b41ba518c4080';
    fetch(`https://notion-api.splitbee.io/v1/page/${NOTION_PAGE_ID}`)
      .then((res) => res.json())
      .then((resJson) => {
        setData(resJson);
      });
  }, []);

  return (
    <div className="my-4">
      <h1 className="mb-4 text-3xl font-bold">{t('guide')}</h1>
      <NotionRenderer blockMap={data} />
    </div>
  );
};

export default GuidePage;
