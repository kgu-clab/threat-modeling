import { useTranslation } from 'react-i18next';

const HomeTitle = () => {
  const { t } = useTranslation();

  return (
    <div className="relative flex flex-col items-center justify-center gap-5 py-16 my-10 text-center">
      <div className="absolute square-pattern dark:square-pattern-dark" />
      <div className="flex justify-center gap-2 text-5xl font-bold leading-normal">
        <h1 className="text-nowrap">
          <span>🛡️&nbsp;</span>
          <span className="text-transparent bg-gradient-to-b from-slate-500 via-slate-700 to-black bg-clip-text dark:to-slate-700 dark:via-slate-600">
            Threat Modeling
          </span>
        </h1>
      </div>
      <div className="max-w-xl space-y-2 break-keep">
        <h2 className="text-xl font-semibold underline-offset-4">
          {t('title')}
        </h2>
        <h3 className="text-sm text-gray-600 dark:text-gray-300">
          {t('subTitle')}
        </h3>
      </div>
    </div>
  );
};

export default HomeTitle;
