const Footer = () => {
  return (
    <footer className="container my-6">
      <hr />
      <p className="my-6 text-xl font-semibold">🛡️ Threat Modeling</p>
      <ul className="text-xs leading-loose">
        <li>
          <a
            href="https://sites.google.com/view/ksel/ds-lab?authuser=0"
            target="_blank"
          >
            Developed By Distributed Security Lab
          </a>
          <span> & </span>
          <a href="https://github.com/KGU-C-Lab" target="_blank">
            Kyonggi University C-Lab
          </a>
        </li>
        <li className="font-semibold">
          <a
            href="https://sites.google.com/view/ksel/ds-lab?authuser=0"
            target="_blank"
          >
            © Distributed Security Lab. All rights reserved.
          </a>
        </li>
      </ul>
    </footer>
  );
};

export default Footer;
