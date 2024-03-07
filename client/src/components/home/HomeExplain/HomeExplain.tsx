import { EXPLAIN } from '@constants/content';
import explainHowIcon from '@assets/svg/explain-how.svg';
import explainWhoIcon from '@assets/svg/explain-who.svg';
import explainResultIcon from '@assets/svg/explain-result.svg';

interface ExplainCardProps {
  index: number;
  title: string;
  description: React.ReactNode;
}

const images = [explainHowIcon, explainWhoIcon, explainResultIcon];

const ExplainCard = ({ index, title, description }: ExplainCardProps) => {
  return (
    <div className="flex flex-col gap-2 p-4 transition-colors border rounded-lg break-keep hover:border-gray-500 last:col-span-2 md:last:col-span-1">
      <img className="h-full p-4 max-h-36" src={images[index]} alt={title} />
      <div className="space-y-2 grow">
        <p className="text-lg font-semibold">{title}</p>
        <p className="text-sm text-gray-600 whitespace-pre-wrap">
          {description}
        </p>
      </div>
    </div>
  );
};

const HomeExplain = () => {
  return (
    <div className="grid grid-cols-2 gap-4 mx-auto md:grid-cols-3">
      {EXPLAIN.map(({ title, description }, index) => (
        <ExplainCard
          key={index}
          index={index}
          title={title}
          description={description}
        />
      ))}
    </div>
  );
};

export default HomeExplain;
