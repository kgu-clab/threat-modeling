import { PATH } from '@constants/path';
import { cn } from '@utils/component';
import { useTranslation } from 'react-i18next';
import { Link, useLocation } from 'react-router-dom';
import ThemeSwitch from '../ThemeSwitch/ThemeSwitch';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '../Select/Select';

const Navbar = () => {
  const location = useLocation();
  const { t, i18n } = useTranslation();

  const handleLanguageChange = (language: string) =>
    i18n.changeLanguage(language);

  const selected = (name: string) => {
    return cn(
      location.pathname === name
        ? 'font-semibold text-black dark:text-white'
        : 'text-gray-500 hover:text-black hover:dark:text-white',
    );
  };

  return (
    <nav className="w-full h-14">
      <div className="container flex items-center justify-center h-full gap-2 text-sm divide-x md:gap-4 md:justify-end text-nowrap">
        <div className="space-x-2 md:space-x-4">
          <Link className={selected(PATH.HOME)} to={PATH.HOME}>
            {t('home')}
          </Link>
          <Link className={selected(PATH.GUIDE)} to={PATH.GUIDE}>
            {t('guide')}
          </Link>
        </div>
        <div className="flex items-center gap-2 pl-2 md:gap-4 md:pl-4">
          <Link
            className={selected(PATH.ROOT)}
            to="https://center-for-threat-informed-defense.github.io/attack-flow/ui/"
            target="_blank"
          >
            Attack Flow Builder
          </Link>
          <Select onValueChange={handleLanguageChange}>
            <SelectTrigger>
              <SelectValue placeholder={i18n.language} />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="English">English</SelectItem>
              <SelectItem value="Korea">{t('korea')}</SelectItem>
            </SelectContent>
          </Select>
          <ThemeSwitch />
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
