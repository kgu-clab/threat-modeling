import Footer from '@/components/common/Footer/Footer';
import ScrollToTop from '@/components/common/ScrollToTop/ScrollToTop';
import Navbar from '@components/common/Navbar/Navbar';
import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <ScrollToTop>
      <Navbar />
      <main className="container">
        <Outlet />
      </main>
      <Footer />
    </ScrollToTop>
  );
};

export default Layout;
