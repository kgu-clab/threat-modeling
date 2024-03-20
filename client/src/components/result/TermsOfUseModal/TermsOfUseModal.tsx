import { NOTION } from '@constants/api';
import { LOCAL_STORAGE_KEY } from '@constants/key';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { NotionRenderer, type BlockMapType } from 'react-notion';

interface TermsOfUseModalProps {
  isOpen: boolean;
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

const TermsOfUseModal = ({ isOpen, setIsOpen }: TermsOfUseModalProps) => {
  const { t } = useTranslation();
  const [data, setData] = useState<BlockMapType>({});

  const handleOpen = (value: boolean) => {
    if (value) localStorage.setItem(LOCAL_STORAGE_KEY.AGREE, value.toString());
    setIsOpen(false);
  };

  useEffect(() => {
    fetch(`https://notion-api.splitbee.io/v1/page/${NOTION.TERMS_OF_USE}`)
      .then((res) => res.json())
      .then((data) => {
        setData(data);
      });
  }, []);

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 p-6 bg-black/20">
      <div className="relative max-w-2xl p-4 space-y-4 transform -translate-x-1/2 -translate-y-1/2 bg-white border rounded-lg shadow-lg Absolute break-keep top-1/2 left-1/2">
        <h1 className="text-3xl font-bold">{t('termsOfUse')}</h1>
        <div className="p-2 overflow-auto border rounded-lg max-h-72">
          <NotionRenderer blockMap={data} />
        </div>
        <div className="text-sm text-center">
          <p>{t('agreeInformation')}</p>
          <p>{t('agreeQuestion')}</p>
        </div>
        <div className="space-x-2 text-sm font-semibold text-center">
          <button
            className="text-gray-500 border rounded px-2 py-0.5"
            onClick={() => handleOpen(false)}
          >
            {t('cancel')}
          </button>
          <button
            className="border rounded px-2 py-0.5 bg-black text-white"
            onClick={() => handleOpen(true)}
          >
            {t('agree')}
          </button>
        </div>
      </div>
    </div>
  );
};

export default TermsOfUseModal;
