import { END_POINT, NOTION } from '@constants/api';
import { useTranslation } from 'react-i18next';
import { useEffect, useState } from 'react';
import { BlockMapType, NotionRenderer } from 'react-notion';

const GuidePage = () => {
  const { t } = useTranslation();
  const [data, setData] = useState<BlockMapType>({});
  /**
   * 노션 데이터를 가져옵니다.
   */
  useEffect(() => {
    fetch(END_POINT.NOTION(NOTION.GUIDE))
      .then((res) => res.json())
      .then((data) => setData(data));
  }, []);

  return (
    <div className="my-4 dark:text-gray-300">
      <h1 className="mb-4 text-3xl font-bold">{t('guide')}</h1>
      <NotionRenderer blockMap={data} />
    </div>
  );
};

export default GuidePage;
