import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer className="container my-6">
      <hr />
      <ul className="my-6">
        <li className="text-xl font-semibold">🛡️ Threat Modeling</li>
      </ul>
      <ul className="text-xs leading-loose">
        <li className="space-x-2 divide-x">
          <Link to="" className="hover:underline">
            이용약관
          </Link>
          <Link to="" className="pl-2 hover:underline">
            개인정보처리방침
          </Link>
          <Link to="" className="pl-2 hover:underline">
            업데이트 내역
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
