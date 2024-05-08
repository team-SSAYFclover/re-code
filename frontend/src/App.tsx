import Layout from '@/components/@common/Layout';
import ErrorPage from '@/pages/error/ErrorPage';
import GuidePage from '@/pages/guide/GuidePage';
import HomePage from '@/pages/home/HomePage';
import ProblemDetailPage from '@/pages/problem/ProblemDetailPage';
import ProblemPage from '@/pages/problem/ProblemPage';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ProblemLayout from './components/@common/ProblemLayout';
import RecodeLayout from './components/@common/RecodeLayout';
import PrivateRoute from './pages/private/PrivateRoute';
import RecodeDetailPage from './pages/recode/RecodeDetailPage';
import RecodePage from './pages/recode/RecodePage';
import RedirectPage from './pages/redirect/RedirectPage';
import { requestPermission, messaging } from '@/utils/firebaseMessageSW.ts';
import { useEffect } from 'react';
import { onMessage } from 'firebase/messaging';
import userStore from './stores/userStore';

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
        path: ':codeId',
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
  const { isLogin } = userStore();

  onMessage(messaging, (payload) => {
    console.log('포그라운드 메시지 수신', payload);
  });

  useEffect(() => {
    if (isLogin) requestPermission();
  }, [isLogin]);

  return (
    <>
      <RouterProvider router={router} />
      <ToastContainer />
    </>
  );
};

export default App;
