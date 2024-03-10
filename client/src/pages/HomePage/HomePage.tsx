import HomeExplain from '@components/home/HomeExplain/HomeExplain';
import HomeTitle from '@components/home/HomeTitle/HomeTitle';

import Dropzone from '@components/common/Dropzone/Dropzone';
import { Link } from 'react-router-dom';
import { PATH } from '@constants/path';
import Hr from '@components/common/Hr/Hr';
import { useTranslation } from 'react-i18next';

const HomePage = () => {
  const { t } = useTranslation();

  return (
    <div>
      <HomeTitle />
      <HomeExplain />
      <Hr className="px-2 space-x-2">
        <Link to={PATH.GUIDE} className="hover:underline">
          {t('guide')}
        </Link>
        <span>•</span>
        <Link
          to="https://center-for-threat-informed-defense.github.io/attack-flow/ui/"
          target="_blank"
          className="hover:underline"
        >
          Attack Flow Builder
        </Link>
      </Hr>
      <Dropzone />
    </div>
  );
};

export default HomePage;
