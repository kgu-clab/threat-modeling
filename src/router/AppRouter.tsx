import { RouterProvider, createBrowserRouter } from "react-router-dom";
import AppLayout from "./AppLayout";
import { PATH } from "@/components/constants/path";
import { Suspense } from "react";
import MainPage from "@/pages/main/MainPage";

const AppRouter = () => {
  const router = createBrowserRouter([
    {
      path: PATH.MAIN,
      element: <AppLayout />,
      children: [
        {
          path: PATH.MAIN,
          element: (
            <Suspense>
              <MainPage />
            </Suspense>
          ),
        },
      ],
    },
  ]);
  return <RouterProvider router={router} />;
};
export default AppRouter;
