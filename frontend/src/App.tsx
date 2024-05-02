import Layout from '@/components/@common/Layout';
import ErrorPage from '@/pages/error/ErrorPage';
import GuidePage from '@/pages/guide/GuidePage';
import HomePage from '@/pages/home/HomePage';
import ProblemPage from '@/pages/problem/ProblemPage';
import ProblemDetailPage from '@/pages/problem/ProblemDetailPage';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ProblemLayout from './components/@common/ProblemLayout';
import PrivateRoute from './pages/private/PrivateRoute';
import RecodePage from './pages/recode/RecodePage';
import RedirectPage from './pages/redirect/RedirectPage';

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
        path: 'recode',
        element: (
          <PrivateRoute>
            <RecodePage />
          </PrivateRoute>
        ),
      },
      {
        path: 'guide',
        element: <GuidePage />,
      },
    ],
  },
  {
    path: '/problem',
    element: (
      <PrivateRoute>
        <ProblemLayout />
      </PrivateRoute>
    ),
    children: [
      {
        path: '',
        element: <ProblemPage />,
      },
      {
        path: ':problemNo',
        element: <ProblemDetailPage />,
      },
    ],
  },
  {
    path: '/redirect',
    element: <RedirectPage />,
  },
  {
    path: '/*',
    element: <ErrorPage />,
  },
]);

const App = () => {
  return (
    <>
      <RouterProvider router={router} />
      <ToastContainer />
    </>
  );
};

export default App;
