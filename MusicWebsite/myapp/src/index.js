import * as React from "react";
import { createRoot } from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
  Route,
  Link,
  Outlet,
} from "react-router-dom";
import Download from "./Routes/Download";
import Library from "./Routes/Library";
import Sidebar from "./components/Sidebar";
import "./App.css";
import Carousel from "./Routes/Carousel";
import { LoginEndPoint } from "./components/LoginEndPoint";

const AppLayout = () => (
  <>
    <Sidebar />
    <Outlet />
  </>
);

const router = createBrowserRouter([
  {
    element: <AppLayout />, 
    children: [
      {
        path: "/",
        element: <Download />,
      },
      {
        path: "download",
        element: <Download />,
      },
      {
        path: "library",
        element: <Library />,
      },
      {
        path: "loginEndPoint",
        element: <LoginEndPoint />,
      },
    ],
  },
]);

createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);