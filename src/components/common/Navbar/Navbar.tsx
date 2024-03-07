import { PATH } from '@constants/path';
import { cn } from '@utils/component';
import { Link, useLocation } from 'react-router-dom';

const Navbar = () => {
  const location = useLocation();

  const selected = (name: string) => {
    return cn(
      location.pathname === name
        ? 'font-semibold text-black'
        : 'text-gray-500 hover:text-black',
    );
  };

  return (
    <nav className="w-full bg-white h-14">
      <div className="container flex items-center justify-center h-full gap-2 text-sm md:divide-x md:justify-end">
        <div className="space-x-6">
          <Link className={selected(PATH.HOME)} to={PATH.HOME}>
            Home
          </Link>
          <Link className={selected(PATH.GUIDE)} to={PATH.GUIDE}>
            Guide
          </Link>
          <Link className={selected(PATH.EXAMPLE)} to={PATH.EXAMPLE}>
            Example
          </Link>
        </div>
        <div className="pl-2 space-x-6">
          <Link
            className={selected(PATH.ROOT)}
            to="https://center-for-threat-informed-defense.github.io/attack-flow/ui/"
            target="_blank"
          >
            Attack Flow Builder
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
