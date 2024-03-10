import { EXPLAIN } from '@constants/content';
import explainHowIcon from '@assets/svg/explain-how.svg';
import explainWhoIcon from '@assets/svg/explain-who.svg';
import explainResultIcon from '@assets/svg/explain-result.svg';
import { useTranslation } from 'react-i18next';

interface ExplainCardProps {
  index: number;
  title: string;
  content: string;
}

const images = [explainHowIcon, explainWhoIcon, explainResultIcon];

const ExplainCard = ({ index, title, content }: ExplainCardProps) => {
  return (
    <div className="flex flex-col gap-2 p-4 transition-colors border rounded-lg break-keep hover:border-gray-500 last:col-span-2 md:last:col-span-1">
      <img className="h-full p-4 max-h-36" src={images[index]} alt={title} />
      <div className="space-y-2 grow">
        <p className="text-lg font-semibold">{title}</p>
        <p className="text-sm text-gray-600 whitespace-pre-wrap">{content}</p>
      </div>
    </div>
  );
};

const HomeExplain = () => {
  const { t } = useTranslation();

  return (
    <div className="grid grid-cols-2 gap-4 mx-auto md:grid-cols-3">
      {EXPLAIN.map(({ title, content }, index) => (
        <ExplainCard
          key={index}
          index={index}
          title={t(title)}
          content={t(content)}
        />
      ))}
    </div>
  );
};

export default HomeExplain;
