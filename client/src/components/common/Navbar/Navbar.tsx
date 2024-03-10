import { PATH } from '@constants/path';
import { cn } from '@utils/component';
import { useTranslation } from 'react-i18next';
import { Link, useLocation } from 'react-router-dom';
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
        ? 'font-semibold text-black'
        : 'text-gray-500 hover:text-black',
    );
  };

  return (
    <nav className="w-full bg-white h-14">
      <div className="container flex items-center justify-center h-full gap-6 text-sm md:gap-4 md:divide-x md:justify-end">
        <div className="space-x-6">
          <Link className={selected(PATH.HOME)} to={PATH.HOME}>
            {t('home')}
          </Link>
          <Link className={selected(PATH.GUIDE)} to={PATH.GUIDE}>
            {t('guide')}
          </Link>
          <Link className={selected(PATH.EXAMPLE)} to={PATH.EXAMPLE}>
            {t('example')}
          </Link>
        </div>
        <div className="flex items-center gap-6 md:pl-4">
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
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
