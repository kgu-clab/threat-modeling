import { END_POINT, NOTION } from '@constants/api';
import { LOCAL_STORAGE_KEY } from '@constants/key';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { NotionRenderer, type BlockMapType } from 'react-notion';

interface TermsOfUseModalProps {
  isOpen: boolean;
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

const TermsOfUseModal = ({ isOpen, setIsOpen }: TermsOfUseModalProps) => {
  const { t } = useTranslation();
  const [data, setData] = useState<BlockMapType>({});
  /**
   * 약관 동의 거절 이벤트
   */
  const handleCancel = useCallback(() => {
    localStorage.removeItem(LOCAL_STORAGE_KEY.AGREE);
    setIsOpen(false);
  }, [setIsOpen]);
  /**
   * 약관 동의 이벤트
   */
  const handleAgree = useCallback(() => {
    localStorage.setItem(LOCAL_STORAGE_KEY.AGREE, new Date().toISOString());
    setIsOpen(false);
  }, [setIsOpen]);
  /**
   * 노션 데이터를 가져옵니다.
   */
  useEffect(() => {
    fetch(END_POINT.NOTION(NOTION.TERMS_OF_USE))
      .then((res) => res.json())
      .then((data) => {
        setData(data);
      });
  }, []);

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 p-6 bg-black/20 dark:bg-white/20">
      <div className="relative max-w-2xl p-4 space-y-4 transform -translate-x-1/2 -translate-y-1/2 bg-white border rounded-lg shadow-lg dark:bg-black Absolute break-keep top-1/2 left-1/2">
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
            className="border rounded text-gray-500 px-2.5 py-1 "
            onClick={handleCancel}
          >
            {t('cancel')}
          </button>
          <button
            className="border rounded px-2.5 py-1 bg-black text-white dark:bg-white dark:text-black"
            onClick={handleAgree}
          >
            {t('agree')}
          </button>
        </div>
      </div>
    </div>
  );
};

export default TermsOfUseModal;
