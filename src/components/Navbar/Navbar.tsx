import { Link, useLocation } from "react-router-dom";

import classNames from "classnames";

const Navbar = () => {
  const location = useLocation();

  const selected = (name: string) => {
    return classNames("hover:font-bold transition", {
      "font-bold underline underline-offset-8": location.pathname === name,
    });
  };

  return (
    <nav className="fixed w-full">
      <div className="bg-white border-b">
        <div className="section flex h-full items-center justify-between px-6 py-1.5">
          <Link className="text-xl font-bold" to="/">
            Threat Modeling
          </Link>

          <div className="hidden gap-6 text-sm sm:flex">
            <Link className={selected("/")} to="/">
              HOME
            </Link>
            <Link className={selected("/help")} to="/help">
              HELP
            </Link>
            <Link className={selected("/upload")} to="/upload">
              UPLOAD
            </Link>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
