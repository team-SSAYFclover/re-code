import Layout from '@/components/@common/Layout';
import ErrorPage from '@/pages/error/ErrorPage';
import GuidePage from '@/pages/guide/GuidePage';
import HomePage from '@/pages/home/HomePage';
import ProblemPage from '@/pages/problem/ProblemPage';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import RecodeLayout from './components/@common/RecodeLayout';
import PrivateRoute from './pages/private/PrivateRoute';
import RecodeDetailPage from './pages/recode/RecodeDetailPage';
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
        path: 'problem',
        element: (
          <PrivateRoute>
            <ProblemPage />
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
    path: '/recode',
    element: (
      <PrivateRoute>
        <RecodeLayout />
      </PrivateRoute>
    ),
    children: [
      {
        path: '',
        element: <RecodePage />,
      },
      {
        path: ':recodeId',
        element: <RecodeDetailPage />,
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
