import ScrollToTop from "@/components/common/ScrollToTop/ScrollToTop";
import { Outlet } from "react-router-dom";

const AppLayout = () => {
  return (
    <ScrollToTop>
      <Outlet />
    </ScrollToTop>
  );
};

export default AppLayout;
