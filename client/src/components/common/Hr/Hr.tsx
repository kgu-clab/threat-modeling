import { cn } from '@utils/component';
import { PropsWithChildren } from 'react';

interface HrProps extends PropsWithChildren {
  className?: string;
}

const Hr = ({ children, className }: HrProps) => {
  return (
    <div className="flex items-center justify-center my-6 text-sm text-center text-gray-600 dark:text-gray-300">
      <hr className="mr-2 grow" />
      <div className={cn(className)}>{children}</div>
      <hr className="ml-2 grow" />
    </div>
  );
};

export default Hr;
