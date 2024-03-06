import { PATH } from '@constants/path';
import { cn } from '@utils/component';
import { Link, useLocation } from 'react-router-dom';

const Navbar = () => {
  const location = useLocation();

  const selected = (name: string) => {
    return cn(
      location.pathname === name ? 'font-semibold text-black' : 'text-gray-500',
    );
  };

  return (
    <nav className="w-full bg-white h-14">
      <div className="container flex items-center justify-end h-full">
        <div className="space-x-6 text-sm">
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
      </div>
    </nav>
  );
};

export default Navbar;
