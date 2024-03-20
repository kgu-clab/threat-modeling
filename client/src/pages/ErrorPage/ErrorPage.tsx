import Footer from '@components/common/Footer/Footer';

import { BiSolidError } from 'react-icons/bi';
import Navbar from '@components/common/Navbar/Navbar';
import { Button } from '@components/common/Button/Button';
import { Link, useNavigate } from 'react-router-dom';
import { PATH } from '@constants/path';
import { useTranslation } from 'react-i18next';

const ErrorPage = () => {
  const { t } = useTranslation();
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center min-h-screen gap-5">
      <Navbar />
      <main className="container flex flex-col items-center justify-center space-y-4 text-center grow">
        <BiSolidError size={128} className="drop-shadow-xl" />
        <div className="drop-shadow-xl">
          <h1 className="text-xl font-semibold">{t('error')}</h1>
          <h2 className="text-lg">{t('errorDescription')}</h2>
          <Link
            to="https://github.com/KGU-C-Lab/threat-modeling"
            className="text-xs underline underline-offset-2"
            target="_blank"
          >
            {t('inquiry')}
          </Link>
        </div>
        <Button size="sm" onClick={() => navigate(PATH.HOME)}>
          {t('goToBack')}
        </Button>
      </main>
      <Footer />
    </div>
  );
};

export default ErrorPage;
