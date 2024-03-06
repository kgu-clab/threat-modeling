import { Badge } from "../Badge/Badge";
import explain1 from "@assets/svg/explain-how.svg";
import explain2 from "@assets/svg/explain-who.svg";
import explain3 from "@assets/svg/explain-result.svg";

interface ExplainCardProps {
  id: number;
  badge: string;
  title: string;
  description: React.ReactNode;
}

const ExplainCard = ({ id, badge, title, description }: ExplainCardProps) => {
  const explainImg = [explain1, explain2, explain3];

  return (
    <div
      key={id}
      className="flex flex-col justify-between w-full p-8 m-2 border rounded-lg"
    >
      <div className="space-y-2 ">
        <Badge>{badge}</Badge>
        <p className="text-lg font-bold">{title}</p>
        <p className="text-gray-500">{description}</p>
      </div>
      <img className="w-auto h-60" src={explainImg[id - 1]} alt={title} />
    </div>
  );
};

export default ExplainCard;
