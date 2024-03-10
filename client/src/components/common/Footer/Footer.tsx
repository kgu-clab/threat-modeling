import { useTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
import pkg from '../../../../package.json';

const Footer = () => {
  const { t } = useTranslation();

  return (
    <footer className="container my-6">
      <hr />
      <ul className="my-6 space-y-2">
        <li className="text-xl font-semibold">🛡️ Threat Modeling</li>
        <li className="flex items-center gap-2">
          <img
            src={`https://img.shields.io/badge/build-${pkg.version}-3178C6`}
          />
          <img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/KGU-C-Lab/threat-modeling" />
        </li>
      </ul>
      <ul className="text-xs leading-relaxed">
        <li className="space-x-2 divide-x">
          <Link to="" className="hover:underline">
            {t('termsOfUse')}
          </Link>
          <Link to="" className="pl-2 hover:underline">
            {t('privacyPolicy')}
          </Link>
          <Link to="" className="pl-2 hover:underline">
            {t('changelog')}
          </Link>
        </li>
        <li>
          <span>Developed By </span>
          <Link
            to="https://sites.google.com/view/ksel/ds-lab?authuser=0"
            target="_blank"
            className="hover:underline"
          >
            Distributed Security Lab
          </Link>
          <span> & </span>
          <Link
            to="https://github.com/KGU-C-Lab"
            target="_blank"
            className="hover:underline"
          >
            Kyonggi University C-Lab
          </Link>
        </li>
        <li className="font-semibold">
          © Distributed Security Lab. All rights reserved.
        </li>
      </ul>
    </footer>
  );
};

export default Footer;
