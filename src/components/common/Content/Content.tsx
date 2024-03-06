interface ContentProps extends React.HTMLAttributes<HTMLDivElement> {}

const Content = ({ children }: ContentProps) => {
  return (
    <div className="items-center w-full space-y-4 rounded-lg">{children}</div>
  );
};

export default Content;
