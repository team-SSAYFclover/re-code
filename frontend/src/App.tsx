import Layout from '@/components/@common/Layout';
import ErrorPage from '@/pages/error/ErrorPage';
import GuidePage from '@/pages/guide/GuidePage';
import HomePage from '@/pages/home/HomePage';
import ProblemPage from '@/pages/problem/ProblemPage';
import RecodePage from '@/pages/recode/RecodePage';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    children: [
      {
        path: '',
        element: <HomePage />,
      },
      {
        path: 'problem',
        element: <ProblemPage />,
      },
      {
        path: 'recode',
        element: <RecodePage />,
      },
      {
        path: 'guide',
        element: <GuidePage />,
      },
    ],
  },
  {
    path: '/*',
    element: <ErrorPage />,
  },
]);

const App = () => {
  return <RouterProvider router={router} />;
};

export default App;
