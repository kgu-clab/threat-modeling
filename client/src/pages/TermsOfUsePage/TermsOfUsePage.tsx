import { NOTION } from '@constants/api';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { NotionRenderer, type BlockMapType } from 'react-notion';

const TermsOfUsePage = () => {
  const { t } = useTranslation();
  const [data, setData] = useState<BlockMapType>({});

  useEffect(() => {
    fetch(`https://notion-api.splitbee.io/v1/page/${NOTION.TERMS_OF_USE}`)
      .then((res) => res.json())
      .then((data) => setData(data));
  }, []);

  return (
    <div className="my-4 break-keep">
      <h1 className="mb-4 text-3xl font-bold">{t('termsOfUse')}</h1>
      <NotionRenderer blockMap={data} />
    </div>
  );
};

export default TermsOfUsePage;
