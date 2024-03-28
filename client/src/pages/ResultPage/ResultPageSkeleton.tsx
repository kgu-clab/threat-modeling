import { useTranslation } from 'react-i18next';
import { LuLoader2 } from 'react-icons/lu';

const ResultPageSkeleton = () => {
  const { t } = useTranslation();

  return (
    <div className="flex flex-col items-center justify-center w-full min-h-screen text-center">
      <LuLoader2 size={32} className="m-2 animate-spin" />
      <p>{t('loading')}</p>
    </div>
  );
};

export default ResultPageSkeleton;
