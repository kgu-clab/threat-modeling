import Footer from '@/components/common/Footer/Footer';
import ScrollToTop from '@/components/common/ScrollToTop/ScrollToTop';
import Navbar from '@components/common/Navbar/Navbar';
import { Suspense } from 'react';
import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <Suspense>
      <ScrollToTop>
        <Navbar />
        <main className="container">
          <Outlet />
        </main>
        <Footer />
      </ScrollToTop>
    </Suspense>
  );
};

export default Layout;
