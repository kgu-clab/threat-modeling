import Footer from "@/components/common/Footer/Footer";
import ScrollToTop from "@/components/common/ScrollToTop/ScrollToTop";
import Navbar from "@/components/Navbar/Navbar";
import { Outlet } from "react-router-dom";

const AppLayout = () => {
  return (
    <ScrollToTop>
      <Navbar />
      <Outlet />
      <Footer />
    </ScrollToTop>
  );
};

export default AppLayout;
